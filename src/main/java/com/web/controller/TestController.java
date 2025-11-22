package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.model.response.OrderRequest;

@Controller
public class TestController {
	@PostMapping("/search")
	public OrderRequest getResponse(OrderRequest orderRequest, Model model) {
		return orderRequest;
	}
}
