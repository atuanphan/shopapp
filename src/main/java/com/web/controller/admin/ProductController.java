package com.web.controller.admin;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.enums.CategoryType;
import com.web.model.dto.ProductDTO;
import com.web.model.request.ProductRequest;
import com.web.model.response.PageResponse;
import com.web.model.response.ProductResponse;
import com.web.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ProductController {
	
	private final ProductService productService;
	private final PageResponse productPageResponse;
	
	@GetMapping(value = "/product-list")
	public ModelAndView productList(@ModelAttribute ProductRequest productRequest, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/product/list");
		List<ProductResponse> products = productService.findAll(productRequest, PageRequest.of(productRequest.getPage() - 1, productRequest.getMaxPageItems()));		
		productPageResponse.setResult(products);
		productPageResponse.setTotalPage(productService.getAdminItemCount());
		mav.addObject("productList", productPageResponse);
		productPageResponse.setPage(productRequest.getPage());
		mav.addObject("modelSearch", productRequest);
		mav.addObject("categoryType", CategoryType.type());
		return mav;
	}
	
	@GetMapping(value = "/product-edit") 
	public ModelAndView productEdit(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/product/edit");
		mav.addObject("productEdit", new ProductDTO());
		mav.addObject("categoryType", CategoryType.type());
		return mav;
	}
	
	@GetMapping(value = "/product-edit-{id}") 
	public ModelAndView productEdit(@PathVariable Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/product/edit");
		mav.addObject("productEdit", productService.findById(id));
		mav.addObject("categoryType", CategoryType.type());
		return mav;
	}
	
}
