package com.web.api.admin;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.service.web.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class OrderManagementAPI {

	private final OrderService orderService;
	
	@DeleteMapping("/delete-order/{ids}")
	public void deleteOrder(@PathVariable Long[] ids) {
		orderService.deleteOrder(ids);
	}
	
	@PostMapping("/change-status")
	public void changeOrderStatus(@RequestBody Long id) {
		orderService.changeOrderStatus(id);
	}
}
