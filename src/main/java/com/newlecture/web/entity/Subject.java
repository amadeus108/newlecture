package com.newlecture.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Subject {
	
	@Id
	private long id;
	private String title;
	private String regUserId;
	@Column(insertable=false)
	private Date regDate;
	private String description;

	public Subject() {

	}

	public Subject(String title, String regUserId, String description) {
		this.title = title;
		this.regUserId = regUserId;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		return "Subject [id=" + id + ", title=" + title + ", regUserId=" + regUserId + ", regDate=" + regDate
				+ ", description=" + description + "]";
	}

}