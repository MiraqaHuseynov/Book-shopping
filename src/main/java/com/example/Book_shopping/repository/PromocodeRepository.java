package com.example.Book_shopping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Book_shopping.entity.Promocode;

public interface PromocodeRepository extends JpaRepository<Promocode, Long> {

	Optional<Promocode> findByCode(String code);
}
