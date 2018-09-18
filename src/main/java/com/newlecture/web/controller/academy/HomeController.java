package com.newlecture.web.controller.academy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("academyController")
@RequestMapping("/academy/")
public class HomeController {
	
	@GetMapping("index")
	public String academy() {
		return "academy/index";
	}
}
