package com.newlecture.aop.spring.anno;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class AfterHandler implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object object) throws Throwable {
		// ��ȯ���� ������ ������ ó���ϰų� �� �� ���Ǵ� ���� : After
		int result = (Integer) returnValue; // object => int
		if (result < 0) {
			System.out.println("0���� �۾ƿ� �����Դϴ�");
		} else {
			System.out.println("����Դϴ�.");
		}
	}

}
