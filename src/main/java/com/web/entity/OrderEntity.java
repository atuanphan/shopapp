package com.web.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "orders")
@Entity
public class OrderEntity extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "total_amount")
	private Long totalAmount;
	
	@Column(name = "shipping_address")
	private String shippingAddress;
	
	@Column(name = "recipient_name")
	private String recipentName;
	
	@Column(name = "recipient_phone")
	private String recipentPhone;
	
	@Column(name = "payment_method")
	private String paymentMethod;
	
	@Column(name = "order_status")
	private String orderStatus;
	
	@Column(name = "notes")
	private String notes;
	
	@OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	private List<OrderDetail> orderDetails = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
}
