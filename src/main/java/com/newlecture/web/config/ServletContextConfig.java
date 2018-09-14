package com.newlecture.web.config;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

//@ComponentScan = <context:component-scan base-package="com.newlecture.web.controller" />
@Configuration
@ComponentScan(basePackages = "com.newlecture.web.controller")
@EnableWebMvc //annotation-driven
public class ServletContextConfig implements WebMvcConfigurer {

	/*
	 * <bean
	 * class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	 * <property name="prefix" value="/WEB-INF/views/" /> <property name="suffix"
	 * value=".jsp" /> <property name="order" value="2" /> </bean>
	 */

	// �� Ŭ������ �����ؼ� �޶�� �ϴ°ǵ�, �޼ҵ� �̸��� �����̳ʿ� ����� �̸� get�� ���� �ȵȴ�.
	/* ���� Ŭ������ ��üȭ �ؼ� ������ �̸����� IoC�� ����ּ��� */
	/*
	 * <bean name="internalResourceViewResolver"
	 * class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	 * <property name="prefix" value="/WEB-INF/views/" /> <property name="suffix"
	 * value=".jsp" /> <property name="order" value="2" /> </bean>
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(2);
		return resolver;
	}

	/*
	 * public int add(Integer... args) { return 0; }
	 */
	
/*	<bean class="org.springframework.web.servlet.view.tiles3.TilesConfigurer">
		<property name="definitions" value="/WEB-INF/tiles.xml" />
	</bean>
	
	<bean class="org.springframework.web.servlet.view.UrlBasedViewResolver">
		<property name="viewClass" value="org.springframework.web.servlet.view.tiles3.TilesView" />
		<property name="order" value="1" />
	</bean>*/

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}

	@Bean
	public UrlBasedViewResolver urlBasedViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setViewClass(TilesView.class);
		resolver.setOrder(1);
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//<mvc:resources location="/resources/" mapping="/resources/**" />
		registry
		.addResourceHandler("/resources/**")
		.addResourceLocations("/resources/");
	}
	
	//한글을 살리자
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// 메세지를 주고받을때 변화를 주고싶다.
		StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		// 요청 헤더에 어떤 인코딩방식을 쓴다고 설정할 수 있는데, 그걸 따라가지 말고 내가 여기서 설정한 방식만 따라가라는 것
		converter.setWriteAcceptCharset(false);
		
		converters.add(converter);
		
		WebMvcConfigurer.super.configureMessageConverters(converters);
	}
	
	//MultipartResolver = 입력에 대한 리졸버(view에 대한게 아니다)
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(1024*1024*100); //업로드하는 전체파일의 맥스사이즈 제한
		resolver.setMaxUploadSizePerFile(1024*1024*10); //업로드파일마다의 맥스사이즈, 기본적으로 여러개의 파일이 오는 경우
		resolver.setDefaultEncoding("UTF-8"); //인코딩 방식을 UTF-8로 지정, Filter와 차이
		
		return resolver;
	}


}









