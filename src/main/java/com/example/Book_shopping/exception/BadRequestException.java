package com.example.Book_shopping.exception;

public class BadRequestException extends RuntimeException {

	private String message;

	public BadRequestException(String message) {

		super(message);
		this.message = message;
	}
}
