package com.example.Book_shopping.model;

import java.util.List;

public class OrderCreateDto {

	private List<Long> bookIds;
	private String promocode;

	public String getPromocode() {
		return promocode;
	}

	public void setPromocode(String promocode) {
		this.promocode = promocode;
	}

	public List<Long> getBookIds() {
		return bookIds;
	}

	public void setBookIds(List<Long> bookIds) {
		this.bookIds = bookIds;
	}

}
