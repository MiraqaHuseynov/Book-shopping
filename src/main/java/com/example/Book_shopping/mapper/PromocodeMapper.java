package com.example.Book_shopping.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.Book_shopping.entity.Promocode;
import com.example.Book_shopping.model.PromocodeDto;

@Component
public class PromocodeMapper {

	public PromocodeDto entityToDto(Promocode promocode) {
        PromocodeDto promocodeDto = new PromocodeDto();
        promocodeDto.setId(promocode.getId());
        promocodeDto.setCode(promocode.getCode());
        promocodeDto.setDiscount(promocode.getDiscount());
        
        return promocodeDto;
    }

    public Promocode dtoToEntity(PromocodeDto promocodeDto) {
        Promocode promocode = new Promocode();
        promocode.setId(promocodeDto.getId());
        promocode.setCode(promocodeDto.getCode());
        promocode.setDiscount(promocodeDto.getDiscount());
       
        return promocode;
    }

    public List<PromocodeDto> entityListToDtoList(List<Promocode> promocodes) {
        return promocodes.stream()
                         .map(this::entityToDto)
                         .toList();
    }
}

