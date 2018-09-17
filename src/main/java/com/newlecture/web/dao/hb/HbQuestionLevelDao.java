package com.newlecture.web.dao.hb;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.newlecture.web.dao.QuestionLevelDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.QuestionLevel;

@Repository
public class HbQuestionLevelDao implements QuestionLevelDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int insert(QuestionLevel level) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Object id = session.save(level);
		if(id != null)
			return 1;
		
		return 0;
	}

	@Override
	public int delete(long id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		QuestionLevel level = new QuestionLevel();
		level.setId(id);
		
		session.remove(level);
		
		return 1;
	}

	@Override
	public int update(QuestionLevel level) {
		Session session = sessionFactory.getCurrentSession();
		
		session.update(level);
		
		return 1; //기존의 설정을 리턴하려면 1
	}

	@Override
	@Transactional
	public QuestionLevel get(long id) {
		Session session = sessionFactory.getCurrentSession();
		
		QuestionLevel level = session.get(QuestionLevel.class, id); //where절에 사용할 인자 id만 설정해주면 된다.
		
		return level;
	}

	@Override
	public List<QuestionLevel> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
