package com.newlecture.web.dao;

import java.util.List;

import com.newlecture.web.entity.QuestionLevel;

public interface QuestionLevelDao {
	
	int insert(QuestionLevel level);

	int delete(long id);

	int update(QuestionLevel level);

	QuestionLevel get(long id);

	List<QuestionLevel> getList();


}
