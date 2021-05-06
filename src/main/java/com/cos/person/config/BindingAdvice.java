package com.cos.person.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// 메모리에 띄워야하는데 해당 advice의 경우 reflect가 controller를 실행 한 뒤 실행이 되어야하므로 @Configuration으로 하지 않고 @Component로해도 됨
// 컨트롤러 진입전 설정이 필요할경우 @Configuration , 그 이후는 Component
@Component  // Controller뜨고 난 뒤 메모리뜸
@Aspect
public class BindingAdvice {

}
