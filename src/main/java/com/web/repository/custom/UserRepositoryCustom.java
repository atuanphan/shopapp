package com.web.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.entity.UserEntity;
import com.web.model.request.UserRequest;

public interface UserRepositoryCustom {
	public Page<UserEntity> findAll(UserRequest userRequest, Pageable pageable, int total);
}
