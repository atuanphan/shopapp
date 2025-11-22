package com.web.model.request;

import lombok.Data;

@Data
public class ProductSearchRequest {
	private String categoryName;
	private String priceSort;
	private String nameProduct;
	private int pageNumber = 1;
	private int maxPageItem = 6;
}
