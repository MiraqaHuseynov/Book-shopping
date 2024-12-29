package com.example.Book_shopping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Book_shopping.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findById(Long id);

	Optional<User> findByUsername(String username);

	boolean existsByUsername(String username);

}
