package com.springau.sellerportal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Documents {
	
	private int sellerId;
	private String panNo;
	private String gstInNo;
	private String panImageType;
	private String gstInImageType;
	private byte[] panImage;
	private byte[] gstInImage;
}
