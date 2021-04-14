package com.grzelak.exercise.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "REQUESTS")
public class Request {

	@Id
	String login;
	
	@Column(name="request_count")
	int requestCount;
	
	public Request() {
		this.login = null;
		this.requestCount = 0;
	}
	
	public Request(String id) {
		this.login = id;
		this.requestCount = 0;
	}

	public String getLogin() {
		return this.login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public int getRequestCount() {
		return this.requestCount;
	}
	
	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}
	
	public void incrementRequestCount() {
		this.requestCount += 1;
	}
	
}
