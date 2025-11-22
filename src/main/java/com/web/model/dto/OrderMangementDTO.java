package com.web.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMangementDTO extends AbstractDTO{
	private Long id;
    private String recipentName;
    private String recipentPhone;
    private String shippingAddress;
    private String paymentMethod;
    private String orderStatus;
    private String totalAmount;
    private List<OrderDetailDTO> orderDetails;

}
