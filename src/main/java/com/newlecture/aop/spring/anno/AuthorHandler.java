package com.newlecture.aop.spring.anno;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class AuthorHandler implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		
		System.out.println("���� Ȯ��");

	}

}
