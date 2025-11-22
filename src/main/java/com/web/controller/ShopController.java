package com.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.web.enums.DistrictType;
import com.web.model.dto.LoginDTO;
import com.web.model.dto.UserDTO;
import com.web.security.util.SecurityUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/davy")
public class ShopController {

	@GetMapping("/shop")
	public ModelAndView getShop() {
		ModelAndView mav = new ModelAndView("web/shop");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    boolean isLoggedIn = auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String);
		mav.addObject("isLoggedIn", isLoggedIn);
		if(isLoggedIn) {
			mav.addObject("fullName", SecurityUtil.getPrincipal().getFullName());
		    mav.addObject("userId", SecurityUtil.getPrincipal().getId());
		}
		return mav;
	}
	
	@GetMapping("/login")
	public ModelAndView getLogin(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("/login");
		mav.addObject("requestLogin", new LoginDTO());
		return mav;
	}
	
	@GetMapping(value = "/access-denied")
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/login?accessDenied");
	}

	@GetMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/shop");
	}
	
	@GetMapping(value = "/register")
	public ModelAndView registerAccount() {
		ModelAndView mav = new ModelAndView("web/register");
		mav.addObject("register", new UserDTO());
		mav.addObject("districtType", DistrictType.type());
		return mav;
	}
}
