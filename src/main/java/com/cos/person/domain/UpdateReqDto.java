package com.cos.person.domain;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdateReqDto {
	@NotBlank(message = "비밀번호를 입력하지 않았습니다.") //이거만해도 null처리까지 하기는 함.
	private String password;
	private String phone;

}
