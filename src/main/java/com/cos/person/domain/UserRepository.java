package com.cos.person.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	
	public List<User> findAll(){
		List<User> users = new ArrayList<>();
		users.add(new User(1,"ridiss","1234","01012345678"));
		users.add(new User(2,"liadus","1234","01012345678"));
		users.add(new User(3,"lilliana","1234","01012345678"));
		return users;
	}
	
	public User findById(int id){
		User user = new User();
		user.setId(id);
		user.setUsername("ridiss");
		user.setPassword("12345");
		user.setPhone("01022224444");
		return user;
	}
	
	public void save(JoinReqDto dto) {
		System.out.println("DB인터트");
	}
	
	public void delete(int id) {
		System.out.println("DB삭제완료");
	}

	public void update(int id, UpdateReqDto dto) {
		throw new IllegalArgumentException("아규먼트를 잘못넣음");
//		System.out.println("DB에 업데이트");
	}
}
