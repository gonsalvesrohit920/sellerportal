package com.springau.sellerportal.queries;

public class CategoryQuestionsQueries {
	
	
	public static final String GET_CATEGORY_FIELDS = "SELECT  cat_question\r\n" + 
														"FROM public.\"Category_questions\" where cat_id =?;";
	
	
	
	private CategoryQuestionsQueries() {
		
	}

}
