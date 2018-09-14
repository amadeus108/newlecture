package com.newlecture.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.QuestionLevelDao;
import com.newlecture.web.entity.QuestionLevel;

@Repository
public class MybatisQuestionLevelDao implements QuestionLevelDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(QuestionLevel level) {
		QuestionLevelDao levelDao = sqlSession.getMapper(QuestionLevelDao.class);
		return levelDao.insert(level);
	}

	@Override
	public int delete(long id) {
		QuestionLevelDao levelDao = sqlSession.getMapper(QuestionLevelDao.class);
		return levelDao.delete(id);
	}

	@Override
	public int update(QuestionLevel level) {
		QuestionLevelDao levelDao = sqlSession.getMapper(QuestionLevelDao.class);
		return levelDao.update(level);
	}

	@Override
	public QuestionLevel get(long id) {
		QuestionLevelDao levelDao = sqlSession.getMapper(QuestionLevelDao.class);
		return levelDao.get(id);
	}

	@Override
	public List<QuestionLevel> getList() {
		QuestionLevelDao levelDao = sqlSession.getMapper(QuestionLevelDao.class);
		return levelDao.getList();
	}

}
