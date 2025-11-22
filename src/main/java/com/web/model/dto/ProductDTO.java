package com.web.model.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
	private Long id;
	
	@NotBlank(message = "Please fill out this field")
	private String name;
	
	@NotBlank(message = "Please fill out this field")
	private String category;
	
	@NotBlank(message = "Please fill out this field")
	private String description;
	
	@NotNull(message = "Value cannot be null")
	private Long price;
	
	@NotNull(message = "Value cannot be null")
	private Long discountPrice;
	
	private MultipartFile imageUrl;
	

	@NotNull(message = "Value cannot be null")
	private Long stockQuantity;

	@NotNull(message = "Value cannot be null")
	private Long weight;
	
	@NotBlank(message = "Please fill out this field")
	private String flavor;
	
	@NotBlank(message = "Please fill out this field")
	private String brand;
	
	private String isFeatured;

}
