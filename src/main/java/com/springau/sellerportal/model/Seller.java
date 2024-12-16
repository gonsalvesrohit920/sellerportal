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
	
	private Contact contact;
	
	private String applicationStatus;
	
	private boolean isValid = true;
	private boolean exists = true;
	
	
	

}
