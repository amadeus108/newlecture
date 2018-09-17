package com.newlecture.web.dao.hb;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.SubjectDao;
import com.newlecture.web.entity.Subject;

@Repository
public class HbSubjectDao implements SubjectDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int insert(Subject subject) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Object id = session.save(subject);
		
		if(id != null)
			return 1;
		
		return 0;
	}

	@Override
	public int delete(long id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Subject subject = new Subject();
		subject.setId(id);
		
		session.remove(subject);
		
		return 1;
	}

	@Override
	public int update(Subject subject) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.update(subject);
		
		return 1;
	}

	@Override
	public Subject get(long id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Subject subject = session.get(Subject.class, id);
		
		return subject;
	}

	@Override
	public List<Subject> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
