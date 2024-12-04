package com.utc.rooming_house.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import com.utc.rooming_house.apis.error.BadRequestAlertException;
import com.utc.rooming_house.configuration.ApplicationProperties;
import com.utc.rooming_house.dto.ResponseDTO;
import com.utc.rooming_house.dto.SearchDTO;
import com.utc.rooming_house.dto.UserDTO;
import com.utc.rooming_house.entity.Role;
import com.utc.rooming_house.entity.User;
import com.utc.rooming_house.repository.RoleRepo;
import com.utc.rooming_house.repository.UserRepo;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

public interface UserService {
	UserDTO create(UserDTO userDTO);

	Boolean delete(String id);

	Boolean deleteAll(List<String> ids);
	
	List<UserDTO> getAll();

	UserDTO get(String id);
	
	User getE(String name);

	UserDTO updatePassword(UserDTO userDTO);
	
	UserDTO update(UserDTO userDTO);
	
	ResponseDTO<List<UserDTO>> search(SearchDTO searchDTO);
	
}

@Service
class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ApplicationProperties props;
	
	@Autowired
	private RoleRepo roleRepo;


	@Override
	@Transactional
	public UserDTO create(UserDTO userDTO) {
		try {
			ModelMapper mapper = new ModelMapper();
			// creatte user
			
			if(userDTO.getExpired()<= 0 || userDTO.getExpired()==null) {
				userDTO.setExpired(Long.parseLong(props.getExpiredTime())*12);
			}
			else {
				userDTO.setExpired(Long.parseLong(props.getExpiredTime())* userDTO.getExpired());
			}
			
			User user = mapper.map(userDTO, User.class);
			
			if(userRepo.findByUsername(userDTO.getUsername()).isPresent()) throw new BadRequestAlertException("user is available", "user", "missing");
			
			if(userDTO.getStatus()==null) user.setStatus(true);
			
			String user_id = UUID.randomUUID().toString().replaceAll("-", "");
			user.setUserId(user_id);
			user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
			
			if(userDTO.getRole() == null) {
				Role role = roleRepo.findByRoleName("USER").orElseThrow(NoResultException::new);
				user.setRole(role);
			}else {
				System.err.println("Not Null");
				Role role = roleRepo.findById(userDTO.getRole().getRoleId()).orElseThrow(NoResultException::new);
				user.setRole(role);
			}
			user.setStatus(true);
			// commit save
			userRepo.save(user);
			return mapper.map(user, UserDTO.class);

		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}

	@Override
	@Transactional
	public Boolean deleteAll(List<String> ids) {
		if(ids.isEmpty()) return false;
		userRepo.deleteAllById(ids);
		return true;
	}

	@Override
	@Transactional
	public UserDTO get(String id) {
		try {
			User user = userRepo.findByUserId(id).orElseThrow(NoResultException::new);
			UserDTO userDTO = new ModelMapper().map(user, UserDTO.class);
			return userDTO;

		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}

	}

	@Override
	@Transactional
	public UserDTO updatePassword(UserDTO userDTO) {
		try {
			User user = userRepo.findByUserId(userDTO.getUserId()).orElseThrow(NoResultException::new);
			user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
			userRepo.save(user);
			UserDTO userResponse = new ModelMapper().map(user, UserDTO.class);
			return userResponse;

		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}

	}

	@Override
	public List<UserDTO> getAll() {
		ModelMapper mapper = new ModelMapper();
		List<User> users  = userRepo.findAll();
		return users
				  .stream()
				  .map(user -> mapper.map(user, UserDTO.class))
				  .collect(Collectors.toList());
	}

	@Override
	public UserDTO update(UserDTO userDTO) {
		try {
			User user = userRepo.findByUserId(userDTO.getUserId()).orElseThrow(NoResultException::new);
			ModelMapper model = new ModelMapper();
			user = model.map(userDTO, User.class);
			user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
			userRepo.save(user);
			return userDTO;

		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}

	@Override
	public Boolean delete(String id) {
		try {
		Optional<User> userOptional = userRepo.findById(id);
		if(userOptional.isEmpty()) return false;
		userRepo.deleteById(id);
		return true;
		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}

	@Override
	public ResponseDTO<List<UserDTO>> search(SearchDTO searchDTO) {
		try {
			return null;
		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}

	@Override
	public User getE(String name) {
		Optional<User> user = userRepo.findByUsername(name);
		if(user.isEmpty()) return null;
		return user.get();
	}
	
	

}
