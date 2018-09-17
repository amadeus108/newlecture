package com.newlecture.web.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.newlecture.web.service.HomeService;

@Component
public class NewlectureAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
/*
    USER-> UI -> DB
    USER-> UI -> DAO -> DB
	USER-> UI -> SERVICE -> DAO -> DB 사용자가 DB를 직접접근할 수 없으니, 사용자와 DB를 연결하는 접점이다. 
	그래서 USER INTERFACE이다.
*/
	
	//Hibernate 설정을 위한 임시 주석처리
	@Autowired
	private HomeService service;
	
	//이동시키기 위한 도구
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		/* ------------- 인터럽트 된 상태에서 로그인 처리 방법  --------------*/
		
		//세션을 얻는다.
		HttpSession sesstion = request.getSession();
		//상태값을 알아낸다. 돌아갈 url
		SavedRequest savedRequest = (SavedRequest) sesstion.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		
		if(savedRequest!=null) {//값이 있으면
			String returnURL = savedRequest.getRedirectUrl();
			redirectStrategy.sendRedirect(request, response, returnURL);
			return;
		}
		/* ------------- 인터럽트 되지 않은 상태에서 로그인 처리 방법  ----------*/

		// 인증된 유저의 정보를 가져온다. authentication에서 얻을 수 있다. 
		String memberId = authentication.getName(); // 사용자 정보를 얻는다. 
		
		// dao crud에 국한된 역할
		// service를 만들지 않으면 dao나 controller에서 처리할일, 우리는 서비스에서 담당한다.
		String roleName = service.getDefaultRoleName(memberId);
		
		//받은 roleName에 따라 분기해야 한다. 
		switch (roleName) {
		case "ROLE_TEACHER" :
			redirectStrategy.sendRedirect(request, response, "/teacher/index");	
			break;
		case "ROLE_ADMIN" :
			redirectStrategy.sendRedirect(request, response, "/admin/index");	
			break;
		default : //디폴트는 학생!!
			redirectStrategy.sendRedirect(request, response, "/student/index");
		}
		
	}

}