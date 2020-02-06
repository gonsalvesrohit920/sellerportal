package com.springau.sellerportal.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginData {
	
	private String username;
	private String password;
	private boolean validate = false;

}
