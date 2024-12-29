package com.example.Book_shopping.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.Book_shopping.entity.User;
import com.example.Book_shopping.exception.NotFoundException;
import com.example.Book_shopping.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {


	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() ->{
					throw new NotFoundException("User not found with given id");
				});
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.build();
	}
}
