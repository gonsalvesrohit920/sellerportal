package com.springau.sellerportal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
	
	private String phoneNo;
	private String street;
	private String city;
	private int pincode;
	

}
