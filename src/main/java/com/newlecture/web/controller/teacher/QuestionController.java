package com.newlecture.web.controller.teacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.web.entity.QuestionLevel;
import com.newlecture.web.entity.Question;
import com.newlecture.web.entity.Subject;
import com.newlecture.web.service.HbTeacherService;

@Controller
@RequestMapping("/teacher/question/")
public class QuestionController {
	
	//Hibernate 설정을 위한 임시 주석처리
	@Autowired
	private HbTeacherService service;
	
	@GetMapping("list")
	public String list(Model model) {
		
		List<Subject> subjects = service.getSubjectList();
		List<QuestionLevel> levels = service.getLevelList();
		
		model.addAttribute("subjects", subjects);
		model.addAttribute("levels", levels);
		
		return "teacher.question.list";
	}
	
	@GetMapping("type")
	public String type(Model model) {
		model.addAttribute("hello", "servlet");
		return "teacher.question.type";
	}
	
	// @RequestMapping(value="reg",method=RequestMethod.GET)
	@GetMapping("choice-reg")
	public String choiceReg() {
		return "teacher.question.choice-reg";
	}

	// @RequestMapping(value="reg",method=RequestMethod.POST)
	@PostMapping("choice-reg")
	public String choiceReg(Question question) {
		return "redirect:type";
	}

}