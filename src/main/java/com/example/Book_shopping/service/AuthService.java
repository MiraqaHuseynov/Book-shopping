package com.example.Book_shopping.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Book_shopping.entity.User;
import com.example.Book_shopping.exception.BadRequestException;
import com.example.Book_shopping.exception.NotFoundException;
import com.example.Book_shopping.model.AuthResponse;
import com.example.Book_shopping.model.LoginRequestDto;
import com.example.Book_shopping.model.RegisterRequestDto;
import com.example.Book_shopping.repository.UserRepository;
import com.example.Book_shopping.security.JwtUtils;

@Service
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtils jwtUtils;

	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtils = jwtUtils;
	}

	public String registerUser(RegisterRequestDto registerRequest) {
		if (userRepository.existsByUsername(registerRequest.getUsername())) {
			throw new BadRequestException("Username is already taken");
		}

		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

		userRepository.save(user);

		return "User registered successfully";
	}

	public AuthResponse loginUser(LoginRequestDto loginRequest) {
		User user = userRepository.findByUsername(loginRequest.getUsername())
				.orElseThrow(() ->{
					throw new NotFoundException("User not found with given id");
				});

		if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid username or password");
		}

		String token = jwtUtils.generateJwtToken(org.springframework.security.core.userdetails.User.builder()
				.username(user.getUsername()).password(user.getPassword()).build());
		AuthResponse response = new AuthResponse();
		response.setToken(token);
		return response;
	}
}