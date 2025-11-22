package com.web.controller.admin;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.model.response.OrderRequest;
import com.web.model.response.PageResponse;
import com.web.service.admin.OrderManagementService;
import com.web.service.web.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class OrderManagementController {

	private final OrderService orderService;
	private final PageResponse pageResponse;
	private final OrderManagementService orderManagementService;
	
	@GetMapping("/order-management")
	public ModelAndView getOrderManagement(OrderRequest orderRequest) {
		ModelAndView mav = new ModelAndView("admin/order/order_management");
		pageResponse.setResult(orderService.getList(orderRequest, PageRequest.of(orderRequest.getPage() - 1, orderRequest.getMaxPageItem())));
		pageResponse.setTotalPage(orderService.getTotalItem());
		mav.addObject("orders", pageResponse);
		mav.addObject("searchOrder", new OrderRequest());
		return mav;
	}
	
	@GetMapping("/order-detail-{id}")
	public ModelAndView getOrderDetail(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("admin/order/order_detail");
		mav.addObject("orderDetail", orderManagementService.getOrderDetailById(id));
		return mav;
	}
}