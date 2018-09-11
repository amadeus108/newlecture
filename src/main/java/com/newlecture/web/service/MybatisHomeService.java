package com.newlecture.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.dao.MemberRoleDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.MemberRole;

@Service
public class MybatisHomeService {
	
	@Autowired
	private MemberRoleDao memberRoleDao;
	
	@Autowired
	private MemberDao memberDao;

	public String getDefaultRoleName(String memberId) {
		// memberRoleDao를 이용
		
		List<MemberRole> list = memberRoleDao.getList(memberId);
		String roleName = "ROLE_STUDENT";
		for(MemberRole role : list) {
			if(role.getDefaultRole())
				roleName = role.getRoleName();
		}
		
		return roleName;
	}

	public boolean isEmailDuplicated(String email) {
		//이메일을 줄테니 DB에 있는지 조회해달라는 단서
		//이메일은 한명당 하나니까 get, 여러명이면 gets
		Member member = memberDao.getByEmail(email);
		//member는 있거나 없거나, 반환된게 있으면 true
		if(member != null) {
			return true;
		}
		return false;
	}

	public boolean isIdDuplicated(String id) {
		Member member = memberDao.get(id);
		if(member != null) {
			return true;
		}
		return false;
	}

}


