package com.Last.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.Last.Model.*;
import com.Last.service.ProductService;


@Controller
public class UserController {

	@Autowired
    private ProductService service;
	
	Boolean valid=true;
	Long editId=(long) -1;
	String editName="";
	String editEmail="";
	
	
	@RequestMapping("/")
	public String home(){
		return "index";
	}
	@RequestMapping("/signIn")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, Model model) {
		String name = request.getParameter("name");
		String email  = request.getParameter("email");
		List<User> example= service.signIn(name, email);
		ModelAndView mav = new ModelAndView("redirect:/display");
		ModelAndView mav2 = new ModelAndView("redirect:/errorMessage");
		if(!example.get(0).equals(null)) {
			valid=true;
			return mav;
		}else {
			valid= false;
			return mav2;
		}
		
	}
	
	@RequestMapping("/display")
	public String displayTable(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) { //buradana devam ettt
		List<User> list=new ArrayList<User>();
		ArrayList<String> name=new ArrayList<String>();
        String queryName = request.getParameter("searchBarName");
        
		if(valid.equals(true)) {
			if(queryName!=null && queryName.length()>0 ) {
				list=service.findName(queryName);
				model.addAttribute("listUser", list);
				
			}else {

				list=service.listAll();
				for(int i=0;i<list.size();i++) {
					name.add(list.get(i).getName());
				}
				session.setAttribute("listName", name);
				model.addAttribute("listUser", list);
			}

				
		    return "table";
		}
		          
		return "index";
		
	}
	@RequestMapping("/addTransfer")
	public String showAddDataPage(Model model) {
		if(valid.equals(true)) {
			User user = new User();
			model.addAttribute("user", user);
			return "addNew";
		}else {
			return "index";
		}
		
	}
	@RequestMapping(value = "/addData" , method = RequestMethod.POST)
	public ModelAndView saveData(@ModelAttribute("user") User user) {
		service.save(user);
		ModelAndView mav = new ModelAndView("redirect:/display");
		return mav;
	}
	
	@RequestMapping(value ="/delete", method = RequestMethod.POST)
	public String deleteUser(@RequestParam(name = "hiddenId") Long id) {
	    service.delete(id);
	    return "redirect:/display";       
	}
	
	@RequestMapping(value ="/fill", method = RequestMethod.POST)
	public String fillTextBox(@RequestParam(name = "hiddenId") Long id, Model model, HttpSession session) {
		editId=id;
		User user = service.get(editId);
		editName = user.getName();
		editEmail = user.getEmail();
		session.setAttribute("boxName", editName);
		session.setAttribute("boxEmail", editEmail);

	    return "redirect:/display";       
	}
	
	@RequestMapping("/update")
	public ModelAndView editData(HttpServletRequest request, HttpServletResponse response,  HttpSession session){
		String newName= request.getParameter("textName"); //name attributeun value su 
		String newEmail= request.getParameter("textEmail");
		User user = new User();
		user.setId(editId);
		user.setName(newName);
		user.setEmail(newEmail);
		service.save(user);
		editId=(long) -1;
		session.setAttribute("boxName", "");
		session.setAttribute("boxEmail", "");
		
		return new ModelAndView("redirect:/display");
		
	}
	
	@RequestMapping("/logout")
	public String logout() {
		valid=false;
		return "index";
		
	}
	
}











