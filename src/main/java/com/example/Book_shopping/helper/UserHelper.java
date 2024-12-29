package com.example.Book_shopping.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Book_shopping.entity.User;
import com.example.Book_shopping.exception.NotFoundException;
import com.example.Book_shopping.repository.UserRepository;

@Component
public class UserHelper {

	@Autowired
	private UserRepository userRepository;

	public User findUserByUserName(String userName) {
		User user = userRepository.findByUsername(userName)
				.orElseThrow(() -> {
			throw new NotFoundException("User not found with given id");

		});

		return user;
	}
}
