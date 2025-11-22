package com.web.model.request;

import org.springframework.web.multipart.MultipartFile;

import com.web.model.dto.AbstractDTO;

import lombok.Data;

@Data
public class ProductRequest extends AbstractDTO{
	private String name;
	private Long priceFrom;
	private Long priceTo;
	private Long stockQuantity;
	private String category;
	private String isFeatured;
	private MultipartFile image;	
}
