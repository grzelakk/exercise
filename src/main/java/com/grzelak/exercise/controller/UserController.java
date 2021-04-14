package com.grzelak.exercise.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.grzelak.exercise.model.GithubUser;
import com.grzelak.exercise.model.User;
import com.grzelak.exercise.service.GithubUserService;
import com.grzelak.exercise.service.RequestService;

import javassist.NotFoundException;

@RestController
public class UserController {

	@Autowired
	GithubUserService ghService;
	
	@Autowired
	RequestService requestService;
	
	private Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@RequestMapping(value = "/users/{id}",
			method = RequestMethod.GET,
			produces = "application/json")
	public User getUser(@PathVariable(value = "id") String id) {
		log.debug("Got request for user: " + id);
		this.requestService.increment(id);
		GithubUser ghUser = null;
		try {
			ghUser = this.ghService.getUser(id);
		} catch (NotFoundException e) {
			log.error("User not found");
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, e.getMessage());
		} catch (IOException e) {
			log.error("Unexpected error: " + e.getMessage());
			throw new ResponseStatusException(
			           HttpStatus.INTERNAL_SERVER_ERROR, "Unrecognized error");
		}
		
		User user = null;
		try {
			user = new User(ghUser);
		} catch (ArithmeticException e) {
			log.error("Arithmetic exception - division by 0");
			throw new ResponseStatusException(
			           HttpStatus.INTERNAL_SERVER_ERROR, "Division by 0");
		}
		log.debug("Returning user: " + user.toString());
		return user;
	}
}