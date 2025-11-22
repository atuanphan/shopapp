package com.web.model.response;

import lombok.Data;

@Data
public class OrderRequest {

	private String id;
	private String name;
	private String status;
	private int page = 1;
	private int maxPageItem = 3;
}
