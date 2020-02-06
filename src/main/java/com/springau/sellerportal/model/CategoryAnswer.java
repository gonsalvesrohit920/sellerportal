package com.springau.sellerportal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohit Gonsalves
 * Contains the Answers for the category specific questions
 */
@Getter
@Setter
@NoArgsConstructor

public class CategoryAnswer {
	
	
	private int productId;
	private int catqId;
	private int catId;
	
	private String catAnswer;

}
