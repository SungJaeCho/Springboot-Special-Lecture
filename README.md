# Springboot-Special-Lecture

1. RestController
2. 기본적인 요청과 응답
3. validation 체크
4. AOP로 로그 남기기
5. 문서로 만들기
6. CORS 개념



* AOP 
 - A라는 함수의 실행전 Pointcut을 설정했을 경우 A함수를 프록시공간 메모리를 띄워서 가져온 후 전/후로 나뉘어 실행함
 
*앞단만 처리하고 싶다면 필터만 사용해도 되나 전/후를 모두 처리하고 싶으면 AOP를 사용한다. (필터+리플렉션이 AOP라고 볼수있음)