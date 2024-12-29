package com.example.Book_shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.Book_shopping.model.PromocodeDto;

import com.example.Book_shopping.service.PromocodeService;

@RestController
@RequestMapping("/v1/api/promocodes")
public class PromocodeController {

	@Autowired
    private PromocodeService promocodeService;

    @GetMapping
    public List<PromocodeDto> getAllPromocodes() {
        return promocodeService.getAllPromocodes();
    }

    @PostMapping
    public PromocodeDto createPromocode(@RequestBody PromocodeDto promocodeDto) {
        return promocodeService.createPromocode(promocodeDto);
    }

}