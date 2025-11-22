package com.web.service.admin.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.converter.OrderConverter;
import com.web.entity.OrderEntity;
import com.web.model.dto.OrderMangementDTO;
import com.web.repository.admin.OrderManagementRepository;
import com.web.service.admin.OrderManagementService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderManagementServiceImpl implements OrderManagementService {
	
	private final OrderManagementRepository orderManagementRepository;
	private final OrderConverter orderConverter;

	@Override
	public OrderMangementDTO getOrderDetailById(Long id) {
		Optional<OrderEntity> orderEntity = orderManagementRepository.findById(id);
		OrderMangementDTO dto = orderConverter.toOrderDTO(orderEntity.get());
		return dto;
	}

}
