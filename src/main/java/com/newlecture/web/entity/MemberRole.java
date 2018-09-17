package com.newlecture.web.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MemberRole {
	
	@EmbeddedId
	private MemberRoleId id; 
	//복합키를 사용하는 경우
	//아래 memberId, roleName을 한번에 사용할 수 있는 class 생성
	//객체가 아이디로 사용되는데 여기에 포함되서 사용된다.
	
//	private String memberId;
//	private String roleName;
	@Column(insertable=false)
	private boolean defaultRole;

	public MemberRole() {
	}

	public MemberRole(String memberId, String roleName, boolean defaultRole) {
		super();
		this.id = new MemberRoleId(memberId, roleName);
//		this.memberId = memberId;
//		this.roleName = roleName;
		this.defaultRole = defaultRole;
	}

	public String getMemberId() {
		return id.getMemberId();
	}

	public void setMemberId(String memberId) {
		this.id.setMemberId(memberId);
	}

	public String getRoleName() {
		return id.getRoleName();
	}

	public void setRoleName(String roleName) {
		this.id.setRoleName(roleName);
	}

	public boolean getDefaultRole() {
		return defaultRole;
	}

	public void setDefaultRole(boolean defaultRole) {
		this.defaultRole = defaultRole;
	}

	@Override
	public String toString() {
		return "MemberRole [id=" + id + ", defaultRole=" + defaultRole + ", getMemberId()=" + getMemberId()
				+ ", getRoleName()=" + getRoleName() + ", getDefaultRole()=" + getDefaultRole() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}


}
