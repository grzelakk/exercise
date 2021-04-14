package com.grzelak.exercise.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.grzelak.exercise.model.GithubUser;

import javassist.NotFoundException;

@Service
public class GithubUserService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	//TODO: Add this to properties
	private static final String GITHUB_USERS_API_URI = "https://api.github.com/users/";
	
	private Logger log = LoggerFactory.getLogger(this.getClass().getName());
    
    public GithubUser getUser(String id) throws NotFoundException, IOException {
    	ResponseEntity<GithubUser> response = null;
    	GithubUser result = null;
    	try {
	    	response = restTemplate.getForEntity(GITHUB_USERS_API_URI + id, GithubUser.class);
	    	result = response.getBody();
	    	
    	} catch (HttpClientErrorException e) {
    		log.error("Exception while accessing the user: " + e.getMessage());
    		if(e.getStatusCode() == HttpStatus.NOT_FOUND) throw new NotFoundException("User not found");
    		throw e;
    	}
    	return result;
    }
}
