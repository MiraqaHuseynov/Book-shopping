package com.example.Book_shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Book_shopping.entity.Order;
import com.example.Book_shopping.entity.Promocode;
import com.example.Book_shopping.exception.NotFoundException;
import com.example.Book_shopping.mapper.PromocodeMapper;
import com.example.Book_shopping.model.PromocodeDto;
import com.example.Book_shopping.repository.PromocodeRepository;

@Service
public class PromocodeService {

	@Autowired
	private PromocodeRepository promocodeRepository;

	@Autowired
	private PromocodeMapper promocodeMapper;

	public Optional<Promocode> findByCode(String code) {
		return promocodeRepository.findByCode(code);
	}

	
	public void assignPromocodeToOrder(Promocode promocode, Order order) {
		if (promocode != null && order != null) {
			order.setPromocode(promocode.getCode());
		} else {
			throw new NotFoundException("Promocode or Order cannot be null");
		}
	}
	
	public List<PromocodeDto> getAllPromocodes() {
		List<Promocode> promocodes = promocodeRepository.findAll();
		return promocodeMapper.entityListToDtoList(promocodes);
	}

	public PromocodeDto createPromocode(PromocodeDto promocodeDto) {
		Promocode promocode = promocodeMapper.dtoToEntity(promocodeDto);
		promocodeRepository.save(promocode);
		return promocodeMapper.entityToDto(promocode);
	}

	public Double applyPromocodeDiscount(Double totalPrice, String code) {
		Promocode promocode = promocodeRepository.findByCode(code)
				.orElseThrow(() -> new NotFoundException("Invalid promocode"));

		return totalPrice - (totalPrice * promocode.getDiscount() / 100);
	}
}