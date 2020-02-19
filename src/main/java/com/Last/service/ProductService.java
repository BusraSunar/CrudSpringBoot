package com.Last.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.Last.Model.User;
import com.Last.Repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repo;
	
	public List<User> listAll(){
		return repo.findAll();
		
	}
	
	public void save (User user) {
		repo.save(user);
	}
	
	public User get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}

	public List<User> signIn(String name, String email) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		Example<User> ex = Example.of(user);
		return repo.findAll(ex);
	}
	
	public List<User> findName(String name){
		User user = new User();
		user.setName(name);
		Example<User> ex = Example.of(user);
		return repo.findAll(ex);
	}
	
	
	
	
}
