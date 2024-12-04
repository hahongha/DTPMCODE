package com.utc.rooming_house.apis;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import com.utc.rooming_house.dto.LoginRequest;
import com.utc.rooming_house.dto.ResponseDTO;
import com.utc.rooming_house.entity.User;
import com.utc.rooming_house.repository.UserRepo;
import com.utc.rooming_house.service.AuthService;
import com.utc.rooming_house.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthAPI {

	@Autowired
	UserService userService;

	@Autowired
	AuthService authService;
	
	@Autowired
	UserRepo userRepo;

	@PostMapping("/signin")
	public ResponseDTO<String> signin(@Valid @RequestBody LoginRequest loginRequest) {
		try {
			Optional<User> userOptional = userRepo.findByUsername(loginRequest.getUsername());
			if (userOptional.isEmpty()) throw new AccessDeniedException("Password wrong !!!");
			User user = userOptional.get();			
			Boolean compare_password = BCrypt.checkpw(loginRequest.getPassword(), user.getPassword());
			
			if (!compare_password)
				throw new AccessDeniedException("Password wrong !!!");

			return authService.signin(loginRequest, user);

		} catch (Exception e) {
			throw Problem.builder().withStatus(Status.INTERNAL_SERVER_ERROR).withDetail("SERVER ERROR").build();
		}
	}
	
}
