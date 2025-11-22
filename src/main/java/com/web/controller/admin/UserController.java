package com.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.enums.DistrictType;
import com.web.model.request.UserRequest;
import com.web.model.response.PageResponse;
import com.web.model.response.UserResponse;
import com.web.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	private final PageResponse productPageResponse;
	@GetMapping("/user-list")
	public ModelAndView userList(@ModelAttribute UserRequest userRequest) {
		ModelAndView mav = new ModelAndView("/admin/user/list");
		List<UserResponse> users = userService.findAll(userRequest, PageRequest.of(userRequest.getPage() - 1, userRequest.getTotalItem()));
		productPageResponse.setResult(users);
		productPageResponse.setTotalPage(userService.getTotalItems());
		productPageResponse.setPage(userRequest.getPage());
		mav.addObject("modelSearch", new UserRequest());
		mav.addObject("userList", productPageResponse);
		mav.addObject("districtType", DistrictType.type());
		return mav;
	}
	
	@GetMapping("/user-edit-{id}")
	public ModelAndView userEdit(@PathVariable Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/admin/user/edit");
		mav.addObject("userEdit", userService.findById(id));
		mav.addObject("districtType", DistrictType.type());
		return mav;
	}
	
	@GetMapping("/user-edit")
	public ModelAndView userEdit(@ModelAttribute UserRequest userRequest) {
		ModelAndView mav = new ModelAndView("/admin/user/edit");
		mav.addObject("userEdit", new UserRequest());
		mav.addObject("districtType", DistrictType.type());
		return mav;
	}
}
