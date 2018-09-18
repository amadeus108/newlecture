package com.newlecture.web.controller.academy.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/academy/admin/mng/")
public class MngController {
	
	@GetMapping("view/header")
	public String viewHeader(){
		
		return "academy.admin.mng.view.header";
	}
	
}
