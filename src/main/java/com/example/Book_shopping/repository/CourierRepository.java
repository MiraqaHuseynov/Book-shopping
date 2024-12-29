package com.example.Book_shopping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Book_shopping.entity.Courier;

public interface CourierRepository extends JpaRepository<Courier, Long> {

	Optional<Courier> findById(Long id);
}
