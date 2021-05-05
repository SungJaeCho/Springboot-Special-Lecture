package com.cos.person.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.person.domain.CommonDto;
import com.cos.person.domain.JoinReqDto;
import com.cos.person.domain.UpdateReqDto;
import com.cos.person.domain.User;
import com.cos.person.domain.UserRepository;

@RestController
public class UserController {
	
	//요즘은 final을 써서 위에 @RequiredArgsConstructor 어노테이션으로 주입함 final은 변하지 않는다는 의미 + 컴파일시 생성이라는 의미도있음
	private UserRepository userRepository;  
	// DI 의존성 주입
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// http://localhost:8080/user
	@GetMapping("/user")
	public CommonDto<List<User>> findAll() {
		System.out.println("findAll()");
		return new CommonDto<>(HttpStatus.OK.value(), userRepository.findAll()); //MessageConverter (JavaObject -> JSON String)
	}
	
	// http://localhost:8080/user/1
	@GetMapping("/user/{id}")
	public CommonDto<User> findById(@PathVariable int id) {
		System.out.println("findById() : id : " + id);
		return new CommonDto<>(HttpStatus.OK.value(), userRepository.findById(id));
	}
	
	@CrossOrigin //CORS정책 푸는 것 (외부 자바스크립트에서 호출가능하게)
	// http://localhost:8080/user
	// x-www.form-urlencoded => request.getParameter()
	// text/plain => @RequestBody 어노테이션
	// application/json => @ResponseBody 어노테이션 + 오브젝트로 받기
	// <?>는 리턴시 어떤값을 리턴할때 정하겠다는 의미임
	// @Valid사용시 @RequestBody앞에 붙여야함
	@PostMapping("/user")
	public CommonDto<?> save(@Valid @RequestBody JoinReqDto dto, BindingResult bindingResult) {
		
		// bindingResult에 에러가 존재할시
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new CommonDto<>(HttpStatus.BAD_REQUEST.value(), errorMap);
		}
		
		System.out.println("save()");
		System.out.println("user : " + dto);
//		System.out.println("data : " +data);
//		System.out.println("username : " +username);
//		System.out.println("password : " +password);
//		System.out.println("phone : " +phone);
		userRepository.save(dto);
		return new CommonDto<>(HttpStatus.CREATED.value(),"OK");
	}
	
	// http://localhost:8080/user/1
	@DeleteMapping("/user/{id}")
	public CommonDto delete(@PathVariable int id) {
		System.out.println("delete");
		userRepository.delete(id);
		return new CommonDto<>(HttpStatus.OK.value());
		
	}
	
	// http://localhost:8080/user/1
	@PutMapping("/user/{id}")
	public CommonDto update(@PathVariable int id, @RequestBody UpdateReqDto dto) {
		System.out.println("update");
		System.out.println("updateReqDto : " + dto);
		userRepository.update(id, dto);
		return new CommonDto<>(HttpStatus.OK.value());
		
	}

}
