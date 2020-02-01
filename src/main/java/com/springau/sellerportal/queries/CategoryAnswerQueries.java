package com.springau.sellerportal.queries;

public class CategoryAnswerQueries {
	
	
	public static final String GET_PRODUCT_ANSWERS = "SELECT cq.cat_question, ca.cat_answer\r\n" + 
			"	FROM \"Category_answers\" as ca,\"Category_questions\" as cq where ca.p_id =? and ca.catq_id = cq.catq_id;";
	
	public static final String SAVE_PRODUCT_ANSWERS = "INSERT INTO \"Category_answers\"(\r\n" + 
			"	p_id, cat_id, catq_id, cat_answer)\r\n" + 
			"	VALUES (?, ?, ?, ?);";
	
	private CategoryAnswerQueries() {
		
	}

}
