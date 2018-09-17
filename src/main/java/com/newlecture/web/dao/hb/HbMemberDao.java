package com.newlecture.web.dao.hb;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;

@Repository
public class HbMemberDao implements MemberDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int insert(Member member) {
		
		Session session = sessionFactory.getCurrentSession();

		Object id = session.save(member);	//member를 save한다고 설정하면 insert가 된다
											//새로 추가된 레코드의 식별자값을 반환한다.
		if(id != null)
			return 1;
		
		return 0; //id가 없으면 1, 있으면 0을 리턴
	}

	@Override
	public int update(Member member) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.update(member);
		
		return 1; //기존의 설정을 리턴하려면 1
	}

	@Override
	public int delete(String id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		//1번 방법
		//Member member = get(id);
		//2번 방법
		Member member = new Member();
		member.setId(id);
		
		session.remove(member);
		
		return 1;
	}

	@Override
	@Transactional //하나의 쿼리를 실행하는거라도 db사용하는건 transactional 
	public Member get(String id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Member member = session.get(Member.class, id); //where절에 사용할 인자 id만 설정해주면 된다.
		
		return member;
	}

	@Override
	public Member getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> getList(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> getList(String field, String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> getList(String field, String query, int page) {
		// TODO Auto-generated method stub
		return null;
	}

}
