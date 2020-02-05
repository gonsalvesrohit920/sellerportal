package com.springau.sellerportal.queries;

public class CategoryQuestionsQueries {
	
	
	public static final String GET_CATEGORY_FIELDS = "SELECT  cat_id,catq_id,cat_question " + 
			"FROM public.\"Category_questions\" where cat_id =?;";

	public static final String GET_CATEGORYS = "SELECT  cat_name " + 
			"FROM public.\"Category\" where cat_id =?;";
	
	public static final String GET_CATEGORY_ID = "SELECT  c_id " + 
			"FROM public.\"Seller_category\" where s_id =?;";
	
	private CategoryQuestionsQueries() {
		
	}

}
