package com.newlecture.web.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MemberRoleId implements Serializable{
	//serializable 은 어떤것부터 가져올래? default값은 맨처음 String memberId

	private String memberId;
	private String roleName;

	//기본 생성자 추가
	public MemberRoleId() {

	}

	public MemberRoleId(String memberId, String roleName) {
		super();
		this.memberId = memberId;
		this.roleName = roleName;
	}

	//getter, setter
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
