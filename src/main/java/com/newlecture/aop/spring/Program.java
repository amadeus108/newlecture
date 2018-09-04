package com.newlecture.aop.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {

	public static void main(String[] args) {

		// cal ��ü ����
		// proxy ��ü ����
		// �� ���� ����
		// �̰͵��� ������ ���ؽ�Ʈ�� �ű��.
		// �������� ���� ��ü�� ����
		// �ƴϸ� ���Ͻø� ���� ���� �ڵ忡���� �� �� ����
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/newlecture/aop/spring/spring-context.xml");
		Calculator cal = (Calculator) context.getBean("cal");
		int result = cal.add(3, 0); // ��¥�� ����ȴ�.
		System.out.println(result);
		
	}

}
