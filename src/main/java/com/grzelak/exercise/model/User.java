package com.grzelak.exercise.model;

import com.grzelak.exercise.utils.UserUtil;

public class User {

	private String id;
	private String login;
	private String name;
	private String type;
	private String avatarUrl;
	private String createdAt;
	private String calculation;
	
	public User(GithubUser ghUser) {
		this.setAvatarUrl(ghUser.getAvatar_url());
		this.setCreatedAt(ghUser.getCreated_at());
		this.setId(Long.toString(ghUser.getId()));
		this.setLogin(ghUser.getLogin());
		this.setName(ghUser.getName());
		this.setType(ghUser.getType());
		this.setCalculation(UserUtil.calculate(ghUser.getFollowers(), ghUser.getPublic_repos()).toString());
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String created) {
		this.createdAt = created;
	}
	public String getCalculation() {
		return calculation;
	}
	public void setCalculation(String calculation) {
		this.calculation = calculation;
	}
	
	
	
}
