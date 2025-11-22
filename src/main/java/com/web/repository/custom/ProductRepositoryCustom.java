package com.web.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.entity.ProductEntity;
import com.web.model.request.ProductRequest;
import com.web.model.request.ProductSearchRequest;

public interface ProductRepositoryCustom {
	public Page<ProductEntity> findAll(ProductRequest productRequest, Pageable pageable, int total);

	public Page<ProductEntity> findProducts(ProductSearchRequest productSearchRequest, Pageable pageable, int total);
}
