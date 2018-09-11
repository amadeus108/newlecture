package com.newlecture.web.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.service.MybatisHomeService;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private MybatisHomeService service;
	/*
	 * 데이터는 시스템에 준하는 이름이어야 한다. 그래서 Mybatis'Home'Service 는 이상하다.
 	 * 페이지당 하나의 서비스를 만들어도 된다. 하지만 그 단위가 너무 작으면 그 윗단으로 올라가서 포괄적으로 만들어도 된다.
 	 * 역할자마다 같은 시스템을 사용하게되면 중복되는 서비스가 생길 수 있다.
	 */
	
	
//	@Autowired
//	private MemberDao memberDao;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("join")
	public String join(Model model) {
		
//		Member member = memberDao.get("flwj");
//		model.addAttribute("member", member);
		return "member.join";
	}

	@GetMapping("login")
	public String login() {
		return "member.login";
	}
	
	@GetMapping("join-email")
	public String joinEmail() {
		
		return "member.join-email";
	}
	
	@GetMapping("email-duplicated-error")
	@ResponseBody //그냥 리턴하면 404에러, 페이지를 찾지 못하니까!
	public String emailDuplicatedError() {
		return"<script>alert('이미 가입된 이메일 입니다.');location.href='join-email';</script>";
	}
	
	@GetMapping("is-id-duplicated")
	@ResponseBody 
	public String isIdDuplicated(String id) {
		boolean duplicated = service.isIdDuplicated(id);
		
		if(duplicated)
			return "true";
		
		return "false";
		//자바스크립트에게 응답을 보내는것. (사용자에게 보여주는게 아니다.)
	}	
	
	@PostMapping("join-email")
	public String joinEmail(String email, HttpServletResponse response) {
		//이미 등록된 email인지 검사
		//error 메세지를 뿌리려면 redirect를 하기 위한 컨트롤러가 따로 필요해진다.
		
		//이메일 중복인가? > yes or no
		boolean duplicated = service.isEmailDuplicated(email);
		
		if(duplicated)
			return "redirect:email-duplicated-error";
		
		//유니크한 id를 뽑아내야 한다. guid
		UUID uuid = UUID.randomUUID(); // + 커스텀
		
		MessageDigest salt = null;
		String digest = null;
		
		//지문 채취 작업
		try {
			salt =  MessageDigest.getInstance("SHA-256");
			salt.update(uuid.toString().getBytes()); // 넘겨줄 값이 byte이다.
			
			//바이트열을 문자열로 바꾸기 위해서 더하기가 반복되어야 한다.
			byte[] key = salt.digest();
			
			// 문자열 연결에 효율적이다. 
			StringBuilder builder = new StringBuilder();
			
			for(byte b : key)
				builder.append(String.format("%02x", b)); //포맷팅
			
			digest = builder.toString(); 
			
			System.out.println(digest);
			//940dc385f2f8b6451fdf736329fedee08f6c5033fe621a146423983b05450442
			
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		
		
		// 표기법으로 데이터를 구분해준다.(json, xml)
		
		
		//쿠키를 심는 작업
		Cookie cookie = new Cookie("joinId", digest); //식별값, 문자열만 담아야 한다.
		
		
		// member로 경로를 줄인다
		cookie.setPath("/member/"); // 경로 member에서만 쓴다.
		cookie.setMaxAge(60*60*24); // 단위
		
		response.addCookie(cookie);
		
/*		
		System.out.println(uuid.toString());
		//f5f245f0-9fec-4b8b-a824-005d565e68c4
*/		
		
		// 메시지 만들기
		MimeMessage message = mailSender.createMimeMessage();
		try {
			//마임 = 멀티미디어 포함
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("noreply@newlecture.com");
			helper.setTo(email);
			helper.setSubject("뉴렉처 회원가입을 위한 인증메일");
			// 이메일보낼때.. 파라미터 이름 줄여서 쓰자~
			helper.setText("<a href='http://localhost:8080/member/join-reg?id="+digest+"&em="+email+"'>가입링크</a>", true);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		} // 쉽게 구현해주는 객체
		
		mailSender.send(message);
		return "member.join-email-info";
		
	}
	
	@GetMapping("join-reg")
	public String joinReg(
			@RequestParam(value="id", defaultValue="") String key, 
			@CookieValue(value="joinId", defaultValue="") String joinId,
			@RequestParam(value="em", defaultValue="") String email,
			Model model) {
		
		//에러가 나는 조건을 찾는다.
//		if(key.equals("") || joinId.equals("") || !key.equals(joinId)) {
//			
//			// join-error페이지 이동
//			return "member.join-error";
//		}
		
		// newelcture@naver.com 에서 앞에 newelcture만 발췌하는 코드
		String uid = email.split("@")[0];
		
		model.addAttribute("uid", uid);
		model.addAttribute("email", email);
		
		return "member.join-reg";
		
	}
	
//	09.11까지 한거, 내일 다시~	
//	@PostMapping("join-reg")
//	public String joinReg(Member member, 
//			@RequestParam("photo-file") MultipartFile photofile) { //member에도 photo가 있기 떄문에 어디로 보내야할지 모름, multipartfile name은 다른이름으로 지정
//		
//		return "redirect: member.join-reg";
//	}
	
}





