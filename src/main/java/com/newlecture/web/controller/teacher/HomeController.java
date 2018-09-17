package com.newlecture.web.controller.teacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.web.entity.Subject;
import com.newlecture.web.service.TeacherService;

@Controller("teacherHomeController")
@RequestMapping("/teacher/")
public class HomeController {
	
	//Hibernate 설정을 위한 임시 주석처리
	@Autowired
	private TeacherService teacherService;

	@RequestMapping("index")
	public String type(Model model) {
		
		List<Subject> subjects = teacherService.getSubjectList();
		model.addAttribute("subjects", subjects);
		return "teacher.index";
		
	}
	
}