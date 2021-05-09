package com.cos.person.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cos.person.domain.CommonDto;

import io.sentry.Sentry;

// 메모리에 띄워야하는데 해당 advice의 경우 reflect가 controller를 실행 한 뒤 실행이 되어야하므로 @Configuration으로 하지 않고 @Component로해도 됨
// 컨트롤러 진입전 설정이 필요할경우 @Configuration , 그 이후는 Component
@Component  // Controller뜨고 난 뒤 메모리뜸
@Aspect
public class BindingAdvice {
	
	
	private static final Logger log = LoggerFactory.getLogger(BindingAdvice.class);

	
	@Before("execution(* com.cos.person.web..*Controller.*(..))") // controller의 모든 함수의 앞/뒤에 실행됨
	public void testCheck() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		System.out.println("@Before주소 :" + request.getRequestURI());
		// request값 처리못하나요?
		// log처리는? 파일처리
		System.out.println("로그를 남겼습니다.");
	}
	@After("execution(* com.cos.person.web..*Controller.*(..))") // controller의 모든 함수의 앞/뒤에 실행됨
	public void testCheck2() {
		System.out.println("후처리 로그를 남겼습니다.");
	}
	
	
	
	// 함수 : 앞제어 @Before (username이 안들어왔으면 내가 강제로 넣어주고 실행)
	// 함수 : 뒤제어 @After (응답만관리)
	// 함수 : 앞, 뒤
	@Around("execution(* com.cos.person.web..*Controller.*(..))") // controller의 모든 함수의 앞/뒤에 실행됨
	public Object vaildCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
		String method = proceedingJoinPoint.getSignature().getName();
		
		System.out.println("type :" +type);
		System.out.println("method :" +method);
		
		Object[] args = proceedingJoinPoint.getArgs();
		
		for (Object arg : args) {
			if(arg instanceof BindingResult) { //arg에 BindingResult가 있으면 동작
				BindingResult bindingResult = (BindingResult) arg; // 다운캐스팅
				
				// bindingResult에 에러가 존재할시
				// 서비스 : 정상적인 화면 -> 사용자요청
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();
					for(FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
						// 로그 레벨 error, warn, info, debug
						log.warn(type +"." +method+"() => 필드 :"+error.getField()+",메시지:"+error.getDefaultMessage());
						Sentry.captureMessage(type +"." +method+"() => 필드 :"+error.getField()+",메시지:"+error.getDefaultMessage());
					}
					return new CommonDto<>(HttpStatus.BAD_REQUEST.value(), errorMap);
				}
			}
		}
		return proceedingJoinPoint.proceed(); // 함수의 스택을 실행해라.
	}

}
