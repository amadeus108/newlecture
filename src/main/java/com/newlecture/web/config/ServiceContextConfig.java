package com.newlecture.web.config;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@ComponentScan(basePackages= {"com.newlecture.web.dao.mybatis", "com.newlecture.web.service"})
@Configuration
public class ServiceContextConfig {

	@Bean
	public BasicDataSource basicDataSource() {

		BasicDataSource basicDataSource = new BasicDataSource();

		// db 연결 설정
		/*mssql-server*/
		basicDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		basicDataSource.setUrl("jdbc:sqlserver://211.238.142.251:1433;databaseName=lecture");
		basicDataSource.setUsername("sist");
		basicDataSource.setPassword("dclass");
		
		/*mysql*/
/*		basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		basicDataSource.setUrl("jdbc:mysql://localhost/newlecturedb?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8");
		basicDataSource.setUsername("hong");
		basicDataSource.setPassword("1234");*/

		// 커넥션풀 설정
		basicDataSource.setRemoveAbandoned(true);
		basicDataSource.setInitialSize(20);
		basicDataSource.setMaxActive(30);
		return basicDataSource;
	}

	
	//객체 만들 때는 SqlSessionFactoryBean, return 할 때는 SqlSessionFactory  ok??
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean(BasicDataSource basicDataSource) throws Exception {
		// IoC 컨테이너를 뒤져서 매개변수 설정에 참조된다. ex) basicDataSource 위에서 설정한 이름 똑같이 해야 한다.
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(basicDataSource);
		sqlSessionFactory.setMapperLocations(
				 new PathMatchingResourcePatternResolver()
				.getResources("classpath:com/newlecture/web/dao/mybatis/mapper/*.xml")
		);
		return sqlSessionFactory.getObject();
	}

	
	//위에서 만든 SqlSesstionFactory를 IoC 컨테이너에서 찾아 매개변수에 자동으로 넣어주고 
	// SqlSesstionTemplate객체를 반환하자, 매개변수에 위에서 설정한 팩토리
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	@Bean
	public JavaMailSender mailSender() {
		//이메일을 보내기 위한 준비작업
		
		//이메일 보낼 때 이용할 smtp 정보
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setDefaultEncoding("UTF-8");
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("amadeus0108@gmail.com");
		mailSender.setPassword("Skscjswo3#");

		//이메일 보낼 때 이용할 설정값
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.smtp.auth", true);
		javaMailProperties.put("mail.smtp.starttls.enable", true);
		javaMailProperties.put("mail.debug", true);
		mailSender.setJavaMailProperties(javaMailProperties);
		
		return mailSender;
		
	}
	

}
