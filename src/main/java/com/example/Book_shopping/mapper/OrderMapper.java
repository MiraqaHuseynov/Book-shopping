package com.example.Book_shopping.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.Book_shopping.entity.Order;
import com.example.Book_shopping.model.CourierDto;
import com.example.Book_shopping.model.OrderDto;

import com.example.Book_shopping.model.UserDto;

@Component
public class OrderMapper {

	public OrderDto entityToDto(Order order) {
		OrderDto orderDto = new OrderDto();
		orderDto.setId(order.getId());
		orderDto.setBooks(order.getBooks());
      
		
		
		if (order.getCourier() != null) {
			CourierDto courierDto = new CourierDto();
			courierDto.setId(order.getCourier().getId());
			courierDto.setName(order.getCourier().getName());
			orderDto.setCourier(courierDto);
		}
		orderDto.setStatus(order.getStatus());
		orderDto.setPromocode(order.getPromocode());
		orderDto.setTotalPrice(order.getTotalPrice());
        
		
		
		if (order.getUser() != null) {
			UserDto userDto = new UserDto();
			userDto.setEmail(order.getUser().getEmail());
			userDto.setName(order.getUser().getName());
			userDto.setUsername(order.getUser().getUsername());
			orderDto.setUser(userDto);
		}

		return orderDto;

	}

	public List<OrderDto> entityListToDtoList(List<Order> orders) {
		List<OrderDto> orderDtos = new ArrayList<>();
		orders.stream().forEach(order -> {
			OrderDto orderDto = entityToDto(order);
			orderDtos.add(orderDto);
		});

		return orderDtos;
	}

}
