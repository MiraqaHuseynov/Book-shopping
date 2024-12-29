package com.example.Book_shopping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Book_shopping.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	Optional<Book> findById(Long id);

	List<Book> findByIdIn(List<Long> ids);

	List<Book> findByPriceBetween(Double minPrice, Double maxPrice);
}
