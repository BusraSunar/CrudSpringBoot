package com.Last.productManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
 
@SpringBootApplication
@ComponentScan(basePackages={"com.Last.Controller", "com.Last.service","com.Last.Repository"})
@EntityScan(basePackages = {"com.Last.Model"} )
@EnableJpaRepositories(basePackages = {"com.Last.Repository"})
public class AppMain {
	
    public static void main(String[] args) {
        SpringApplication.run(AppMain.class, args);
    }
}