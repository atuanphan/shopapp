package com.web.controller.web;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.web.enums.CategoryType;
import com.web.model.request.CartRequest;
import com.web.model.request.ProductSearchRequest;
import com.web.model.response.PageResponse;
import com.web.model.response.ProductResponse;
import com.web.security.util.SecurityUtil;
import com.web.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductsController {
    
	private final ProductService productService;
	private final PageResponse productPageResponse;
	
	@GetMapping("/products-list")
	public ModelAndView getProducts(@ModelAttribute ProductSearchRequest productSearchRequest) {
		ModelAndView mav = new ModelAndView("web/products");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    boolean isLoggedIn = auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String);
		mav.addObject("isLoggedIn", isLoggedIn);
		if(isLoggedIn) {
			mav.addObject("fullName", SecurityUtil.getPrincipal().getFullName());
		    mav.addObject("userId", SecurityUtil.getPrincipal().getId());
		}
		List<ProductResponse> products = productService.findProducts(productSearchRequest, PageRequest.of(productSearchRequest.getPageNumber() - 1, productSearchRequest.getMaxPageItem()));
		productPageResponse.setResult(products);
		productPageResponse.setTotalPage(productService.getTotalItems());
		mav.addObject("products", productPageResponse);
		mav.addObject("categoryType", CategoryType.type());
		mav.addObject("productSearch", new ProductSearchRequest());
		return mav;
	}

	@GetMapping("/products-detail-{id}") 
	public ModelAndView productSearch(@PathVariable Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("web/product-details");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    boolean isLoggedIn = auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String);
		mav.addObject("isLoggedIn", isLoggedIn);
		if(isLoggedIn) {
			mav.addObject("fullName", SecurityUtil.getPrincipal().getFullName());
			mav.addObject("userId", SecurityUtil.getPrincipal().getId());
		}
		mav.addObject("request", new CartRequest());
		mav.addObject("productDetails", productService.findById(id));
		return mav;
	}
	

}
