package com.grzelak.exercisetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import com.grzelak.exercise.controller.UserController;
import com.grzelak.exercise.model.GithubUser;
import com.grzelak.exercise.model.User;
import com.grzelak.exercise.repositories.RequestRepository;
import com.grzelak.exercise.service.GithubUserService;
import com.grzelak.exercise.service.RequestService;
import com.grzelak.exercise.utils.UserUtil;

import javassist.NotFoundException;

@SpringBootTest
public class UserControllerTests {

	@Mock
	GithubUserService ghService;
	
	@Mock
	RequestService requestService;
	
	@Autowired
	RequestRepository requestRepository;
	
	@InjectMocks
	UserController controller;
	
	@Test
	public void happyPath() throws NotFoundException, IOException {
		GithubUser ghUser = new GithubUser();
		ghUser.setAvatar_url("avatar_url");
		ghUser.setCreated_at("created_at");
		ghUser.setFollowers(11L);
		ghUser.setId(1234L);
		ghUser.setLogin("SampleName");
		ghUser.setName("Name");
		ghUser.setPublic_repos(22L);
		ghUser.setType("User");
		
		Mockito.when(ghService.getUser("SampleName")).thenReturn(ghUser);
		
		User usr = controller.getUser("SampleName");
		BigDecimal calculation = UserUtil.calculate(11L, 22L);
		
		assertEquals(usr.getAvatarUrl(), "avatar_url");
		assertEquals(usr.getCalculation(),calculation.toString());
		assertEquals(usr.getCreatedAt(), "created_at");
		assertEquals(usr.getId(), Long.toString(1234L));
		assertEquals(usr.getLogin(), "SampleName");
		assertEquals(usr.getName(), "Name");
		assertEquals(usr.getType(), "User");
	}
	
	@Test
	public void arithmeticException() throws NotFoundException, IOException {
		GithubUser ghUser = new GithubUser();
		ghUser.setAvatar_url("avatar_url");
		ghUser.setCreated_at("created_at");
		ghUser.setFollowers(0L);
		ghUser.setId(1234L);
		ghUser.setLogin("SampleName");
		ghUser.setName("Name");
		ghUser.setPublic_repos(22L);
		ghUser.setType("User");
		
		Mockito.when(ghService.getUser("SampleName")).thenReturn(ghUser);
		
		Throwable exception = assertThrows(ResponseStatusException.class, () -> controller.getUser("SampleName"));
		
		assertEquals(exception.getMessage(), "500 INTERNAL_SERVER_ERROR \"Division by 0\"");
	}
	
	
}
