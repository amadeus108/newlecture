package com.newlecture.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QuestionLevel {

	@Id
	private long id;
	private String name;
	/*DB에 저장된 컬럼명이 REG_USER_ID 고, 내 프로젝트에서는 아래 regUserId라는 컬럼명으로 쿼리를 만들겠다고 알려주는것*/
	@Column(name="REG_USER_ID")
	private String regUserId;
	@Column(insertable=false)
	private Date regDate;
	private String description;

	public QuestionLevel() {

	}

	public QuestionLevel(String name, String regUserId, String description) {
		this.name = name;
		this.regUserId = regUserId;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getRegUserId() {
		return regUserId;
	}

	public void setRegUserId(String regUserId) {
		this.regUserId = regUserId;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", regUserId=" + regUserId + ", regDate=" + regDate
				+ ", description=" + description + "]";
	}

}
