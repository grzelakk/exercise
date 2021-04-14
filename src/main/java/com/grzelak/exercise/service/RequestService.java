package com.grzelak.exercise.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grzelak.exercise.model.Request;
import com.grzelak.exercise.repositories.RequestRepository;

@Service
public class RequestService {

	@Autowired
	RequestRepository repository;
	
	public Optional<Request> get(String id) {
		return this.repository.findById(id);
	}
	
	public void increment(String id) {
		Request value = this.repository.findById(id).orElseGet(() -> new Request(id));
		value.incrementRequestCount();
		this.repository.save(value);
	}
}
