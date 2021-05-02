package com.cos.person.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	// http://localhost:8080/user
	@GetMapping("/user")
	public void findAll() {
		System.out.println("findAll()");
	}
	
	// http://localhost:8080/user/1
	@GetMapping("/user/{id}")
	public void findById(@PathVariable int id) {
		System.out.println("findById()");
	}
	
	// http://localhost:8080/user
	// x-www.form-urlencoded => request.getParameter()
	@PostMapping("/user")
	public void save(String username, String password, String phone) {
		System.out.println("save()");
		
	}
	
	// http://localhost:8080/user/1
	@DeleteMapping("/user/{id}")
	public void delete(@PathVariable int id) {
		System.out.println("delete");
		
	}
	
	// http://localhost:8080/user/1
	@PutMapping("/user/{id}")
	public void update(@PathVariable int id, String password, String phone) {
		System.out.println("update");
		
	}

}
