package com.newlecture.web.dao.hb;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.newlecture.web.dao.MemberRoleDao;
import com.newlecture.web.entity.MemberRole;

@Repository
public class HbMemberRoleDao implements MemberRoleDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int insert(MemberRole memberRole) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Object id = session.save(memberRole);
		
		if(id != null)
			return 1;
		
		return 0;
	}

	@Override
	public int update(MemberRole memberRole) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.update(memberRole);
		
		return 1;
	}

	@Override
	public int delete(MemberRole memberRole) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.remove(memberRole);
		
		return 1;
	}

	@Override
	@Transactional
	public MemberRole get(MemberRole memberRole) {
		
		Session session = sessionFactory.getCurrentSession();
		
		MemberRole role = session.get(MemberRole.class, memberRole.getMemberId());
		
		return role;
	}

	@Override
	public List<MemberRole> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberRole> getList(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

}
