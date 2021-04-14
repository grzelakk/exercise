package com.grzelak.exercise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grzelak.exercise.model.Request;

public interface RequestRepository extends JpaRepository<Request, String>{
	
}
