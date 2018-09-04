package com.newlecture.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Program {

	public static void main(String[] args) {

		Calculator cal = new NewlecCalculator();
		
		//cal�� ����, ������ ���� ������� �ʰ� ���Ͻ�(�븮��)�� ���� ����Ѵ�.
		
		//loader(������ü)�� �δ�, �������̽�, ��ٸ�����
		Calculator calProxy = (Calculator) Proxy.newProxyInstance(
				NewlecCalculator.class.getClassLoader(), 
				new Class[] {Calculator.class}, 
				new InvocationHandler() {
					//������ �ִ� ���� invoke
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						
						//��ٸ� ������ �ȴ´�.
						long start = System.currentTimeMillis();
						//--------------------------------------
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
						}
						//���� ��ü�� ȣ���Ͽ� ��ȯ�Ѵ�. 
						Object result = method.invoke(cal, args);
						//--------------------------------------
						long end = System.currentTimeMillis();
						String between = (end - start) + "ms�� �ɸ�";
						System.out.println(between);
						
						return result;
					}
				});
		
		int result = calProxy.add(10, 5); // ��¥�� ����ȴ�.
		System.out.println(result);

	}

}
