package com.springau.sellerportal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seller {
	
	private int id;
	private String name;
	private String email;
	private String password;
	private String phoneNo;
	
	private Contact contact;
	
	
	private String application_status;
	

}
