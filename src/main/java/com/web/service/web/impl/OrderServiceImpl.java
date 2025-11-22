package com.web.service.web.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.converter.OrderConverter;
import com.web.entity.CartEntity;
import com.web.entity.OrderDetail;
import com.web.entity.OrderEntity;
import com.web.entity.ProductEntity;
import com.web.enums.OrderStatusType;
import com.web.model.dto.OrderDTO;
import com.web.model.dto.OrderDTOs;
import com.web.model.dto.OrderDetailDTO;
import com.web.model.dto.OrderMangementDTO;
import com.web.model.response.OrderRequest;
import com.web.model.response.OrderResponse;
import com.web.model.response.PaymentSuccessResponse;
import com.web.repository.admin.OrderManagementRepository;
import com.web.repository.web.CartProductRepository;
import com.web.repository.web.CartRepository;
import com.web.repository.web.OrderRepository;
import com.web.service.web.OrderService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final OrderConverter orderConverter;
	private final CartRepository cartRepository;
	private final CartProductRepository cartProductRepository;
	private final OrderManagementRepository orderManagementRepository;

	@Override
	public void getPlaceOrder(OrderDTO orderDTO) {
		OrderEntity orderEntity = orderConverter.getOrderEntity(orderDTO);
		orderRepository.save(orderEntity);	
		CartEntity cartEntity = cartRepository.findByUserId(orderDTO.getUserId());
		for(ProductEntity item : OrderResponse.productEntities) {
			cartProductRepository.deleteByCartIdAndProductId(cartEntity.getId(), item.getId());
		}
	}

	@Override
	public PaymentSuccessResponse getResponse(Long userId) {
		PaymentSuccessResponse paymentSuccessResponse = new PaymentSuccessResponse();
		OrderEntity orderEntity = orderRepository.findTopByOrderByIdDesc();
		Optional<OrderEntity> optional = orderRepository.findByIdAndUserId(orderEntity.getId() ,userId);
		paymentSuccessResponse.setOrderStatus(optional.get().getOrderStatus());
		paymentSuccessResponse.setPaymentMethod(optional.get().getPaymentMethod());
		paymentSuccessResponse.setRecipientName(optional.get().getRecipentName());
		paymentSuccessResponse.setRecipientPhone(optional.get().getRecipentPhone());
		paymentSuccessResponse.setShippingAddress(optional.get().getShippingAddress());
		Long totalPrice = 0L;
		for(OrderDetail item : optional.get().getOrderDetails()) {
			totalPrice += item.getSubTotal();
		}
		paymentSuccessResponse.setTotalPrice(NumberFormat.getNumberInstance(Locale.US).format(totalPrice).replace(",", "."));
		return paymentSuccessResponse;
	}

	@Override
	public List<OrderDTOs> getOrdersByUser(Long userId) {
        List<OrderEntity> orders = orderRepository.findByUserId(userId);
        List<OrderDTOs> result = new ArrayList<OrderDTOs>();
        for(OrderEntity item : orders) {
        	OrderDTOs orderDTO = orderConverter.toOrderDTOs(item);
        	result.add(orderDTO);
        }
        return result;
    }

	@Override
	public List<OrderMangementDTO> getList(OrderRequest orderRequest, Pageable pageable) {
		Long id = null;
		if(orderRequest.getId() != null && !orderRequest.getId().equals("")) {
			id = Long.parseLong(orderRequest.getId().substring(4));
		}
		Page<OrderEntity> orders = orderRepository.findAll(pageable);
		List<OrderMangementDTO> result = new ArrayList<>();
		if(id != null) {
			orders = orderRepository.findByUser_Id(id, pageable);
		}
		if(orderRequest.getStatus() != null && !orderRequest.getStatus().equals("")) {
			orders = orderRepository.findByOrderStatus(orderRequest.getStatus(), pageable);
		}
		for(OrderEntity item : orders) {
			OrderMangementDTO order = orderConverter.toOrderDTO(item);
			result.add(order);
		}
		return result;
	}

	@Override
	public void deleteOrder(Long[] ids) {
		if(ids != null) {
			orderManagementRepository.deleteByIdIn(ids);
		}
	}

	@Override
	public void changeOrderStatus(Long id) {
		OrderEntity orderEntity = orderRepository.findById(id).get();
		orderEntity.setOrderStatus(OrderStatusType.PROCESSING.name());
		orderRepository.save(orderEntity);
	}

	@Override
	public int getTotalItem() {
	    double total = Math.ceil(orderRepository.count()/3);
		return (int) total;
	}
}

