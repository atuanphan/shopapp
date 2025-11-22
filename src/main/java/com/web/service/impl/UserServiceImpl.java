package com.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.converter.UserConverter;
import com.web.entity.UserEntity;
import com.web.model.dto.UserDTO;
import com.web.model.request.UserRequest;
import com.web.model.response.UserResponse;
import com.web.repository.UserRepository;
import com.web.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	@Override
	public List<UserResponse> findAll(UserRequest userRequest, Pageable pageable) {
		Page<UserEntity> result = userRepository.findAll(userRequest, pageable, getTotalItems());
		List<UserResponse> userResponses = new ArrayList<>();
		for(UserEntity userEntity : result) {
			UserResponse user = userConverter.covertToUserResponse(userEntity);
			if(user.getStatus() == 1) {
				userResponses.add(user);
			}			
		}
		return userResponses;
	}

	@Override
	public UserDTO findById(Long id) {
		UserEntity userEntity = userRepository.findById(id).get();
		UserDTO userDTO = userConverter.covertToUserDTO(userEntity);
		return userDTO;
	}

	@Override
	public UserDTO addOrUpdateUser(UserDTO userDTO) {
		UserEntity userEntity = userConverter.convertToUserEntity(userDTO);
		userRepository.save(userEntity);
		return userConverter.covertToUserDTO(userEntity);
	}

	@Override
	public void deleteUser(List<Long> ids) {
		for(Long id : ids) {
			UserEntity userEntity = userRepository.findById(id).get();
			userEntity.setStatus(0);
			userRepository.save(userEntity);
		}	
	}

	@Override
	public int getTotalItems() {
		double total = userRepository.count() / 5;
		return (int) Math.ceil(total);
	}

}
