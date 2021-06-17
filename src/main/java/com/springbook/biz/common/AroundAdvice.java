package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

public class AroundAdvice {
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		String method = pjp.getSignature().getName();
		
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		//System.out.println("[BEFORE]: ����Ͻ� �޼ҵ� ���� ���� ó���� ����..");
		
		Object obj = pjp.proceed();
		
		stopwatch.stop();
		System.out.println(method + "() �޼ҵ� ���࿡ �ɸ� �ð� : " + stopwatch.getTotalTimeMillis() + "(ms)��");
		return obj;
	}
}