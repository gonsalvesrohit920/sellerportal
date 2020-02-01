package com.springau.sellerportal.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Seller {
	
	private int id;
	private String name;
	private String email;
	private String password;
	
	private Contact contact;
	
	private String applicationStatus;
	
	private boolean isValid = true;
	private boolean exists = true;
	
	private Documents documents;
	

}
