package com.cos.person.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.person.domain.User;
import com.cos.person.domain.UserRepository;

@RestController
public class UserController {
	
	private UserRepository userRepository; //요즘은 final을 써서 위에 @RequiredArgsConstructor 어노테이션으로 주입함 final은 변하지 않는다는 의미 + 컴파일시 생성이라는 의미도있음 
	// DI 의존성 주입
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// http://localhost:8080/user
	@GetMapping("/user")
	public List<User> findAll() {
		System.out.println("findAll()");
		return userRepository.findAll(); //MessageConverter (JavaObject -> JSON String)
	}
	
	// http://localhost:8080/user/1
	@GetMapping("/user/{id}")
	public User findById(@PathVariable int id) {
		System.out.println("findById() : id : " + id);
		return userRepository.findById(id);
	}
	
	// http://localhost:8080/user
	// x-www.form-urlencoded => request.getParameter()
	// text/plain => @RequestBody 어노테이션
	// application/json => @ResponseBody 어노테이션 + 오브젝트로 받기
	@PostMapping("/user")
	public ResponseEntity<String> save(@RequestBody User user) {
		System.out.println("save()");
		System.out.println("user : " + user);
//		System.out.println("data : " +data);
//		System.out.println("username : " +username);
//		System.out.println("password : " +password);
//		System.out.println("phone : " +phone);
		userRepository.save(user);
		return new ResponseEntity<>("OK", HttpStatus.OK);
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
