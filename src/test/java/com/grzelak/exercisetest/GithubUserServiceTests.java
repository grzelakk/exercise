package com.grzelak.exercisetest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.grzelak.exercise.model.GithubUser;
import com.grzelak.exercise.service.GithubUserService;

import javassist.NotFoundException;

@SpringBootTest
public class GithubUserServiceTests {

	@Mock
    private RestTemplate restTemplate;
	
	@InjectMocks
	private GithubUserService service;
	
	@Test
	public void happyPath() throws NotFoundException, IOException {
		String login = "SomeName";
		GithubUser gUser = new GithubUser();
		gUser.setId(1234L);
		gUser.setFollowers(10L);
		gUser.setName(login);
		gUser.setPublic_repos(2L);
		
		Mockito.when(restTemplate.getForEntity("https://api.github.com/users/" + login, GithubUser.class))
			.thenReturn(new ResponseEntity<GithubUser>(gUser, HttpStatus.OK));
		
		GithubUser user = service.getUser(login);
		
		assertTrue(gUser.equals(user));
	}
	
	@Test
	public void userNotFound() {
		String login = "SomeName";
		Mockito.when(restTemplate.getForEntity("https://api.github.com/users/" + login, GithubUser.class))
			.thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
		
		Throwable exception = assertThrows(NotFoundException.class, () -> service.getUser(login));
		
		assertTrue(exception.getClass().equals(NotFoundException.class));
	}
}
