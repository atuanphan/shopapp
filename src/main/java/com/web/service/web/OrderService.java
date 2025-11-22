package com.web.service.web;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web.model.dto.OrderDTO;
import com.web.model.dto.OrderDTOs;
import com.web.model.dto.OrderMangementDTO;
import com.web.model.response.OrderRequest;
import com.web.model.response.PaymentSuccessResponse;

public interface OrderService {
	public void getPlaceOrder(OrderDTO orderDTO);
	public PaymentSuccessResponse getResponse(Long userId);
	public List<OrderDTOs> getOrdersByUser(Long userId);
	public List<OrderMangementDTO> getList(OrderRequest orderRequest, Pageable pageable);
	public void deleteOrder(Long[] ids);
	public void changeOrderStatus(Long id);
	public int getTotalItem();
}
