package com.newlecture.web.dao.hb;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.RoleDao;
import com.newlecture.web.entity.Role;

@Repository
public class HbRoleDao implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int insert(Role role) {
		Session session = sessionFactory.getCurrentSession();

		Object name = session.save(role);	//member를 save한다고 설정하면 insert가 된다
											//새로 추가된 레코드의 식별자값을 반환한다.
		if(name != null)
			return 1;
		
		return 0;
	}

	@Override
	public int update(Role role) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.update(role);
		
		return 1;
	}

	@Override
	public int delete(String id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		return 0;
	}

	@Override
	public Role get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
