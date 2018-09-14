package com.newlecture.web.dao;

import java.util.List;

import com.newlecture.web.entity.Question;

public interface QuestionDao {
	
	int insert(Question question);

	int delete(long id);

	int update(Question question);

	Question get(long id);

	List<Question> getList(String query, String ownerId, String sortField, int page);

}
