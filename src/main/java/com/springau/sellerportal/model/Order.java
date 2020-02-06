package com.springau.sellerportal.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Order {
	
	int orderId;
	int customerId;
	int sellerId;
	int productId;
	int quantity;
	double rating;
	
}
