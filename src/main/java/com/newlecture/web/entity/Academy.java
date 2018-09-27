package com.newlecture.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Academy {
	@Id
	private int id;
	private String name;
	
	public Academy() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Academy(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Academy [id=" + id + ", name=" + name + "]";
	}
	
	
}
