package com.newlecture.aop.spring.anno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
public class CalculatorAspectHandler {

	// pointcut ���� ��� �Ѵ�. ����� ��� @Before
	@Before(value = "execution (* *..NewlecCalculator.add(..))")
	public void authorHandler() {
		System.out.println("���� ����");
	}

	// pointcut ���� ��� �Ѵ�. ����� ��� @After
	@AfterReturning(pointcut = "execution (* *..NewlecCalculator.add(..))", returning = "returnValue")
	public void after(JoinPoint joinPoint, Object returnValue) {
		int result = (Integer) returnValue;
		if (result < 0)
			System.out.println("������ ��ȯ�Ͽ����ϴ�.");
	}

	// pointcut ���� ��� �Ѵ�. ����� ��� @AfterThrowing(pointcut�� Throwable������)
	@AfterThrowing(pointcut = "execution (* *..NewlecCalculator.div(..))", throwing = "e")
	public void errorHandler(JoinPoint joinPoint, Throwable e) {
		System.out.println("�˼��մϴ�. ������ �߻��߽��ϴ�.");
	}

	// pointcut ���� ��� �Ѵ�. ����� ��� @Around(pointcut)
	@Around(value = "execution (* *..NewlecCalculator.add(..))")
	public Object logHandler(ProceedingJoinPoint joinPoint) {
		// ������ ��������ũ�� �����ϴ� Ÿ�̸�
		StopWatch watch = new StopWatch();
		watch.start();
		// --------------------------------------

		Object result = null;
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		// --------------------------------------
		watch.stop();
		long duration = watch.getTotalTimeMillis();
		String message = duration + "ms�� �ɸ�";
		System.out.println(message);

		return result;
	}

}