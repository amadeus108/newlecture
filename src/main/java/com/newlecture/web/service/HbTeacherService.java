package com.newlecture.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.web.dao.QuestionLevelDao;
import com.newlecture.web.dao.QuestionDao;
import com.newlecture.web.dao.SubjectDao;
import com.newlecture.web.entity.QuestionLevel;
import com.newlecture.web.entity.Question;
import com.newlecture.web.entity.Subject;

//@Repository, @Controller, @Service  의미가 있는 컴포넌트
@Service
public class HbTeacherService implements TeacherService {
	
	
	@Autowired
	private SubjectDao subjectDao;
	
	@Autowired
	private QuestionLevelDao questionLevelDao;
	
	@Autowired
	private QuestionDao questionDao;
	

	@Override
	public List<Subject> getSubjectList() {
		
		return subjectDao.getList();
	}

	@Override
	public List<QuestionLevel> getLevelList() {
		
		return questionLevelDao.getList();
	}
	
	/*가장 인자가 많은 것부터 만들고, 그 메서드를 이용해서 인자를 빼고 기본값을 넣어주자*/
	@Override
	public List<Question> getQuestionList() {
		
		return getQuestionList("", null, "regDate", 1);
	}

	@Override
	public List<Question> getQuestionList(String query) {
		
		return getQuestionList(query, null, "regDate", 1);
	}

	@Override
	public List<Question> getQuestionList(String query, int page) {
		
		return getQuestionList(query, null, "regDate", page);
	}

	@Override
	public List<Question> getQuestionList(String query, String ownerId, int page) {
		
		return getQuestionList(query, ownerId, "regDate", page);
	}

	@Override
	public List<Question> getQuestionList(String query, String ownerId, String sortField, int page) {
		
		return questionDao.getList(query, ownerId, sortField, page);
	}

}