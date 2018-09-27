package com.newlecture.web.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //Hibernate에서 사용할 Entity라는 설정(Mapping)
//@Table(name="User") //DB와 테이블명이 다른 경우 테이블명 설정
public class Member {

	@Id //where절에 사용할 인자가 무엇인지 설정
	private String id;
	private String name;
	private String email;
	private String pwd;
	@Column(insertable=false) //regDate는 insert할때 사용하지 말라는 설정(regDate는 default값이 들어가니까)
	private Date regDate;
	//@Column(name="FOTO") //DB와 컬럼명이 다른 경우 설정
	private String photo; // 사용자가 선택한 사진 파일만 담는다.
	
	public Member() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Member(String id, String name, String email, String pwd, Date regDate, String photo) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.regDate = regDate;
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", email=" + email + ", pwd=" + pwd + ", regDate=" + regDate
				+ ", photo=" + photo + "]";
	}

	

}
