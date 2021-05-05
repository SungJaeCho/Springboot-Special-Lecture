# Springboot-Special-Lecture

1. RestController
2. 기본적인 요청과 응답
3. validation 체크
4. AOP로 로그 남기기
5. 문서로 만들기
6. CORS 개념


<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile22.uf.tistory.com%2Fimage%2F9983FB455BB4E5D30C7E10"  width="700" height="370"><br/>

ㆍInterceptor와 Filter는 Servlet 단위에서 실행된다. <> 반면 AOP는 메소드 앞에 Proxy패턴의 형태로 실행된다.<br/>
ㆍ실행순서를 보면 Filter가 가장 밖에 있고 그안에 Interceptor, 그안에 AOP가 있는 형태이다.

ㆍAOP
 - A라는 함수의 실행전 Pointcut을 설정했을 경우 A함수를 프록시공간 메모리를 띄워서 가져온 후 전/후로 나뉘어 실행함
 - 앞단만 처리하고 싶다면 필터만 사용해도 되나 전/후를 모두 처리하고 싶으면 AOP를 사용한다. (필터+리플렉션이 AOP라고 볼수있음)
 