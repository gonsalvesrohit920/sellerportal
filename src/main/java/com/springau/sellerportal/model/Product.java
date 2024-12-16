package com.springau.sellerportal.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	private int productId; 
	private int sellerId;
	private int category;
	private int quantity;
	private int price;
	private String name;
	private String decription;
	private List<ProductImage> images;
	private Map<String, String> attributes;
	
}
