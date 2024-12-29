package com.example.Book_shopping.model;

import java.util.List;

import com.example.Book_shopping.entity.Book;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class OrderDto {

	private long id;

	private Double totalPrice;

	@Enumerated(EnumType.STRING)
	private Status status;

	private UserDto user;

	private CourierDto courier;

	private List<Book> books;

	private String promocode;

	public String getPromocode() {
		return promocode;
	}

	public void setPromocode(String promocode) {
		this.promocode = promocode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public CourierDto getCourier() {
		return courier;
	}

	public void setCourier(CourierDto courier) {
		this.courier = courier;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
