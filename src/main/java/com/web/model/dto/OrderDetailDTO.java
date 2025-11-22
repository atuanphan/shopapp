package com.web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private String productName;
    private String productImage;
    private Long quantity;
    private Long unitPrice;
    private Long subTotal;
}