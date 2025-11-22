package com.web.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.entity.OrderEntity;

public interface OrderManagementRepository extends JpaRepository<OrderEntity, Long>{

	public void deleteByIdIn(Long[] ids);
}
