package com.example.Book_shopping.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Book_shopping.entity.Book;
import com.example.Book_shopping.entity.Courier;
import com.example.Book_shopping.entity.Order;

import com.example.Book_shopping.entity.User;
import com.example.Book_shopping.exception.NotFoundException;
import com.example.Book_shopping.helper.UserHelper;
import com.example.Book_shopping.mapper.OrderMapper;
import com.example.Book_shopping.model.OrderCreateDto;
import com.example.Book_shopping.model.OrderDto;
import com.example.Book_shopping.model.Status;
import com.example.Book_shopping.repository.BookRepository;
import com.example.Book_shopping.repository.CourierRepository;
import com.example.Book_shopping.repository.OrderRepository;

import com.example.Book_shopping.security.JwtUtils;

@Service
public class OrderService {

	@Autowired
	private UserHelper userHelper;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CourierRepository courierRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private PromocodeService promocodeService; 
	
	public List<OrderDto> getAllOrders() {
		String userName = jwtUtils.getAuthenticatedUsername();
		User user = userHelper.findUserByUserName(userName);
		List<Order> orders = orderRepository.findByUserId(user.getId());
		List<OrderDto> orderDtos = orderMapper.entityListToDtoList(orders);
		return orderDtos;
	}

	public OrderDto getUserOrderDetails(Long orderId) {
		String userName = jwtUtils.getAuthenticatedUsername();
		User user = userHelper.findUserByUserName(userName);
		Order order = orderRepository.findByUserIdAndId(user.getId(), orderId);
		if (order == null) {
			throw new NotFoundException("Order not found with given id");
		}
		OrderDto orderDto = orderMapper.entityToDto(order);
		return orderDto;
	}

	public OrderDto createOrder(OrderCreateDto orderCreateDto) {
		String userName = jwtUtils.getAuthenticatedUsername();
		User user = userHelper.findUserByUserName(userName);
		
		List<Book> books = bookRepository.findByIdIn(orderCreateDto.getBookIds());
		Order order = new Order();
		order.setUser(user);
		order.setBooks(books);
		order.setStatus(Status.ORDERED);
		
		
		Double totalPrice = calculateTotalPrice(books);
	
		
	
		String promocodeInput = orderCreateDto.getPromocode();
	    if (promocodeInput != null && !promocodeInput.isEmpty()) {
	        
	        totalPrice = promocodeService.applyPromocodeDiscount(totalPrice, promocodeInput);
	        order.setPromocode(promocodeInput);

	    }
	    
	
	    order.setTotalPrice(totalPrice);
	    orderRepository.save(order);

	    return orderMapper.entityToDto(order);
	}
	

	private Double calculateTotalPrice(List<Book> books) {
        return books.stream().mapToDouble(Book::getPrice).sum();
	}

	public OrderDto assingOrderToCourier(Long orderId, Long courierId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> {
			throw new NotFoundException("Order not found with given id");
		});

		Courier courier = courierRepository.findById(courierId).orElseThrow(() -> {
			throw new NotFoundException("Courier not found with given id");
		});

		order.setCourier(courier);
		order.setStatus(Status.DONE);
		orderRepository.save(order);
		return orderMapper.entityToDto(order);
	}

	public OrderDto cancelOrder(Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new NotFoundException("Order not found with given id"));

	//	if (order.getStatus() == Status.DONE || order.getStatus() == Status.CANCELED) {
		//	throw new NotFoundException("Cannot cancel this order as it is already completed or canceled.");
		//}

		if (order.getCourier() == null) {
			order.setStatus(Status.CANCELED);
			orderRepository.save(order);
		} else {
			throw new NotFoundException("Cannot cancel the order as a courier has been assigned.");
		}
		return orderMapper.entityToDto(order);

	}
	  
}
