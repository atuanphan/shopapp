package com.web.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web.model.dto.ProductDTO;
import com.web.model.request.ProductRequest;
import com.web.model.request.ProductSearchRequest;
import com.web.model.response.ProductResponse;

public interface ProductService {
	public List<ProductResponse> findAll(ProductRequest productRequest, Pageable pageable);
	public ProductResponse findById(Long id);
	public ProductDTO addOrUpdateProduct(ProductDTO productDTO);
	public void deleteProducts(List<Long> ids);
	public List<ProductResponse> findProducts(ProductSearchRequest productSearchRequest, Pageable pageable);
	public int getTotalItems();
	public int getAdminItemCount();
}
