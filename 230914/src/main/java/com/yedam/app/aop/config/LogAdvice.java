package com.yedam.app.aop.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect /* aspect설정. Aspect를 실행시키는 빈은 따로있다.*/
@Component /* 컨테이너에 빈 등록 */
public class LogAdvice {
	// 포인트컷 : 조인포인트(비즈니스 로직과 관련된 메소드)중에서 Advice(공통로직)가 적용될 메소드
	//@Pointcut("execution(* *..*ServiceImpl.*(..))")
	@Pointcut("within(*..*ServiceImpl)")
	public void allPointCut() {
		
	}

	// Weaving : 포인트컷 + Advice + 동적시점
	@Around("allPointCut()") /* 위에 선언한 메소드를 대상으로 */
	public Object logger(ProceedingJoinPoint joinpoint) throws Throwable {
		// Aop가 적용되는 메서드의 이름
		String signatureStr = joinpoint.getSignature().toString();
		System.out.println("start : " + signatureStr);

		// 비즈니스 메소드 실행 전 Advice를 실행하는 시점
		System.out.println("핵심 기능 전 실행 - 공통기능 : " + System.currentTimeMillis());

		try {
			Object obj = joinpoint.proceed(); // 실제 비즈니스 메소드 실행 코드
			return obj;
		} finally {// 비즈니스 메소드 실행 후 Advice를 실행하는 시점
			System.out.println("핵심 기능 후 실행 - 공통기능 :" + System.currentTimeMillis());
		}
	}
}
