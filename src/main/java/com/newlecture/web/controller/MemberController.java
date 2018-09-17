package com.newlecture.web.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.service.HomeService;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	//Hibernate 적용을 위한 임시 주석처리
	@Autowired
	private HomeService service;
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
	
	@GetMapping("join-invalid-error")
	@ResponseBody //그냥 리턴하면 404에러, 페이지를 찾지 못하니까!
	public String joinInvalidError() {
		return"<script>alert('계산식이 올바르지 않습니다..');location.href='join-reg';</script>";
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
		cookie.setMaxAge(60*60*24); // 초 단위
		
		response.addCookie(cookie);
		
		/*		
			System.out.println(uuid.toString());
			f5f245f0-9fec-4b8b-a824-005d565e68c4
		 */		
		
		// 메시지 만들기
		MimeMessage message = mailSender.createMimeMessage();
		try {
			//마임 = 멀티미디어 포함
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("amadeus0108@gmail.com");
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
	
	@PostMapping("join-reg")
	public String joinReg(
			Member member, 
			@RequestParam("photo-file") MultipartFile photoFile,
			Integer moonjae, 
			HttpServletRequest request) throws IOException { //member에도 photo가 있기 떄문에 어디로 보내야할지 모름, multipartfile name은 다른이름으로 지정
		
		//session에 저장된 값을 가져오기
		HttpSession session = request.getSession();
		Integer moonjaeSaved = (Integer) session.getAttribute("moonjae");

		//사용자가 입력한 정답과 session에 저장되있던 moonjae를 꺼내서 비교
		//결과를 처리하기 전에, 사용자가 입력한 moonjae가 정답이 아니면 보내온 정보가 다 필요없게 된다. 조건 검사해주기
		if(moonjae != moonjaeSaved) //유효하지 않은 경우
			return "member.join-invalid-error";
		
		/* 
		 * "/resources/users/계정명/" 프로필 사진 저장경로, 한폴더에 많이 파일이 들어가면 검색속도가 느려진다.
		 * ->"d:\home\www\ROOT\resources\ users\newlec" 윈도우즈에 저장하는 경우
		 * ->"/var/local/web/resources/users/newlec" unix에 저장하는 경우
		 */
		
		String resLocation = "/resources/users/newlec/";
		
		ServletContext context = request.getServletContext();
		String homeDir = context.getRealPath(resLocation);
		String uploadedFileName = photoFile.getOriginalFilename(); //파일명을 가져온다. photoFile.getName() 는 키값이 오는거
		//String filePath = homeDir + File.separator + uploadedFileName; //File.separator는 윈도우, 유닉스 환경에 따라 /, \ 를 넣어준다
		String filePath = homeDir + uploadedFileName;
		System.out.println(filePath);
		
		File dir = new File(homeDir);

		//존재하는지에 대한 질문, 없으면 디렉토리를 만들어라.
		if(!dir.exists())
			dir.mkdirs();
		
		InputStream fis = photoFile.getInputStream(); //multipartfile로 전달받아야만 한다.
		FileOutputStream fos = new FileOutputStream(filePath); //filePath를 근거로 만들어야한다.
		
		//fis에서 읽어서 fos으로 복사하기
		int size = 0;
		byte[] buf = new byte[1024];
		while((size = fis.read(buf, 0, buf.length)) != -1)
			fos.write(buf, 0, size);
		
		fis.close();
		fos.close(); //닫아주지 않으면 장기간 파일을 열고 있는 경우, 시스템을 계속 잡아먹고 있는다.
		
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPwd = encoder.encode(member.getPwd()); //컨트롤러에서 해야하냐, 서비스에서 해야하냐? => 컨트롤러에서 하는 방법
		
		member.setPhoto(uploadedFileName);
		member.setPwd(encodedPwd); //암호화된 비밀번호를 insert해야 한다.
		
		System.out.println(encodedPwd);
		
		//컨트롤러에서는 Dao말고 Service만 생각하자.
		service.insertMember(member);
		
		return "redirect: ../index";
	}
	
	@GetMapping("reset-pwd")
	public String resetPwd() {
		
		return "member.reset-pwd";
	}
	
/*	@GetMapping("reset-pwd")
	public String resetPwd(String id) {
		
		Member member = service.getMember(id); //MemberDao를 직접 사용하지 않는다.
		String email = member.getEmail();
		
		//unique key generating
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
		
		//mail sending
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			//마임 = 멀티미디어 포함
			helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("amadeus0108@gmail.com");
			helper.setTo(email);
			helper.setSubject("뉴렉처 비밀번호 재설정을 위한 인증메일");
			// 이메일보낼때.. 파라미터 이름 줄여서 쓰자~
			helper.setText("<a href='http://localhost:8080/member/pwd-reg?id="+digest+"&em="+email+"'>비밀 인증메일</a>", true);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		} // 쉽게 구현해주는 객체
		
		mailSender.send(message);
		
		return "member.reset-pwd";
	}
*/	
	
	@GetMapping("moonjae.jpg") //실제 폴더에 저장되진 않지만 이미지 폴더에 있는것처럼 맵핑
	public void moonjae(HttpSession session, HttpServletResponse response) throws IOException {
		//랜덤한 계산값을 뽑아내기
		Random random = new Random();

		int x = random.nextInt(10) + 1;
		int y = random.nextInt(10);
		//String result = x+"+"+y+"=";
		String result = String.format("%d+%d=", x, y);
		
		//계산결과를 session에 저장해두기
		session.setAttribute("moonjae", x+y);
		
		BufferedImage img = new BufferedImage(60, 30, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		g.setFont(new Font("돋움", 0, 13)); //폰트 설정
		g.setColor(Color.white); //배경색 설정
		g.fillRect(0, 0, 60, 30); //배경색 넣기
		g.setColor(Color.black); //글자색 설정
		g.drawString(result, 5, 20); //이미지 만들기
		
		response.setContentType("image/jpg"); //브라우저에게 보내는게 이미지라는걸 알려줘야함
		ImageIO.write(img, "png", response.getOutputStream()); //이미지를 만들었으면 그 이미지를 쉽게 사용자에게 전달하게 해준다.
		
	}
	
}





