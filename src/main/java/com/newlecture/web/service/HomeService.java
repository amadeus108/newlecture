package com.newlecture.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.dao.MemberRoleDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.MemberRole;

public interface HomeService {
	
	public String getDefaultRoleName(String memberId);

	public boolean isEmailDuplicated(String email);

	public boolean isIdDuplicated(String id);

	public int insertMember(Member member);

	public Member getMember(String id);

}


