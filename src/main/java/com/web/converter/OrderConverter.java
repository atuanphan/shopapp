package com.web.converter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.entity.CartEntity;
import com.web.entity.CartProductEntity;
import com.web.entity.OrderDetail;
import com.web.entity.OrderEntity;
import com.web.entity.ProductEntity;
import com.web.enums.OrderStatusType;
import com.web.model.dto.OrderDTO;
import com.web.model.dto.OrderDTOs;
import com.web.model.dto.OrderMangementDTO;
import com.web.model.response.OrderResponse;
import com.web.repository.web.CartProductRepository;
import com.web.repository.web.CartRepository;
import com.web.repository.web.OrderDetailRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderConverter {
	
	private final ModelMapper modelMapper;
	private final CartRepository cartRepository;
	private final CartProductRepository cartProductRepository;
	private final OrderDetailRepository orderDetailRepository;
	
	public OrderEntity getOrderEntity(OrderDTO orderDTO) {
		OrderEntity orderEntity = modelMapper.map(orderDTO, OrderEntity.class);
		orderEntity.getUser().setId(orderDTO.getUserId());
		orderEntity.setOrderStatus(OrderStatusType.PENDING.name());
		List<OrderDetail> orderDetails = addOrderDetail(orderDTO, orderEntity);
		orderEntity.setOrderDetails(orderDetails);
		return orderEntity;
	}
	
	public List<OrderDetail> addOrderDetail(OrderDTO orderDTO, OrderEntity orderEntity) {
		CartEntity cartEntity = cartRepository.findByUserId(orderDTO.getUserId());
		List<OrderDetail> orderDetails = new ArrayList<>();
		for (ProductEntity item : OrderResponse.productEntities) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setProduct(item);
			orderDetail.getProduct().setId(item.getId());
			Optional<CartProductEntity> optional = cartProductRepository.findByCartAndProduct(cartEntity, item);
			orderDetail.setQuantity(optional.get().getQuantity());
			orderDetail.setUnitPrice(item.getDiscountPrice());
			orderDetail.setSubTotal(optional.get().getQuantity() * item.getDiscountPrice());
			OrderResponse.subTotal = optional.get().getQuantity() * item.getDiscountPrice();
			orderDetail.setOrder(orderEntity);
			orderDetails.add(orderDetail);
		}
		return orderDetails;
	}
	
	public OrderMangementDTO toOrderDTO(OrderEntity orderEntity) {
		OrderMangementDTO orderDTOs = modelMapper.map(orderEntity, OrderMangementDTO.class);
		orderDTOs.setOrderStatus(OrderStatusType.type().get(orderEntity.getOrderStatus()));
		List<OrderDetail> details = orderDetailRepository.findByOrderId(orderEntity.getId());
		Long total = 0L;
		for(OrderDetail item : details) {
			total += item.getSubTotal();
		}
		String totalAmount = NumberFormat.getNumberInstance(Locale.US).format(total).replace(",", ".");
		orderDTOs.setTotalAmount(totalAmount);
		return orderDTOs;
	}
	
	public OrderDTOs toOrderDTOs(OrderEntity orderEntity) {
		OrderDTOs orderDTO = modelMapper.map(orderEntity, OrderDTOs.class);
		orderDTO.setOrderStatus(OrderStatusType.type().get(orderEntity.getOrderStatus()));
		List<OrderDetail> details = orderDetailRepository.findByOrderId(orderEntity.getId());
		Long total = 0L;
		for(OrderDetail item : details) {
			total += item.getSubTotal();
		}
		String totalAmount = NumberFormat.getNumberInstance(Locale.US).format(total).replace(",", ".");
		orderDTO.setTotalAmount(totalAmount);
		return orderDTO;
	}
}
