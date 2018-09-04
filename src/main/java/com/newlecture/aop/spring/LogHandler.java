package com.newlecture.aop.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

public class LogHandler implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {

		// ������ ��������ũ�� �����ϴ� Ÿ�̸�
		StopWatch watch = new StopWatch();
		watch.start();
		// --------------------------------------

		Object result = method.proceed();

		// --------------------------------------
		watch.stop();
		long duration = watch.getTotalTimeMillis();
		String message = duration + "ms�� �ɸ�";
		System.out.println(message);

		return result;
	}

}
