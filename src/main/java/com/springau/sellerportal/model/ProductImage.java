package com.springau.sellerportal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {
	
	private int imageId;
	private int productId;
	private String imageType;
	private byte[] productImageData;
	
}
