package com.newlecture.aop.spring.anno.hong;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MessengerAspectHandler {

	@Before(value = "execution (* *..KakaoMessenger.sendText(..))")
	public void auth() {
		System.out.println("before ��� �۾��� �ϱ� ���� �غ��۾� or ���� �۾�");
	}
	
}