package com.example.Book_shopping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Book_shopping.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByUserId(Long userId);

	Order findByUserIdAndId(Long userId, Long Id);

	Optional<Order> findById(Long id);

}
