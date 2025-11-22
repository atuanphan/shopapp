package com.web.converter;

import java.text.NumberFormat;
import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.entity.ProductEntity;
import com.web.enums.CategoryType;
import com.web.model.dto.ProductDTO;
import com.web.model.response.ProductResponse;

@Component
public class ProductConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public ProductResponse toProductResponse(ProductEntity productEntity) {
		ProductResponse productResponse = modelMapper.map(productEntity, ProductResponse.class);
		productResponse.setCategory(CategoryType.valueOf(productEntity.getCategory()).getCategoryName());
		String status = productEntity.getIsFeatured()== true? "Đang bán" : "Ngừng bán";
		productResponse.setIsFeatured(status);
		return productResponse;
	}
	
	public ProductEntity converterToProductEntity(ProductDTO productDTO) {
		ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
		productEntity.setIsFeatured(true);
		return productEntity;
	}
	
	public ProductDTO converterToProductDTO(ProductEntity productEntity) {
		ProductDTO productDTO = modelMapper.map(productEntity, ProductDTO.class);
		productEntity.setIsFeatured(true);
		return productDTO;
	}
	
	public ProductResponse converterToProductResponse(ProductEntity productEntity) {
		ProductResponse productResponse = modelMapper.map(productEntity, ProductResponse.class);
		String price = NumberFormat.getNumberInstance(Locale.US).format(productEntity.getPrice()).replace(",", ".");
		String discountPrice = NumberFormat.getNumberInstance(Locale.US).format(productEntity.getDiscountPrice()).replace(",", ".");
		productResponse.setPrice(price + "đ");
		productResponse.setDiscountPrice(discountPrice + "đ");
		return productResponse;
	}
	
	public ProductResponse converterToProductResponseEdit(ProductEntity productEntity) {
		ProductResponse productResponse = modelMapper.map(productEntity, ProductResponse.class);
		return productResponse;
	}

}
