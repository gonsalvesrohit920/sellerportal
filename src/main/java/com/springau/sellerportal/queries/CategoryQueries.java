package com.springau.sellerportal.queries;

public class CategoryQueries {
	
	public static final String GET_CATEGORY_ID = "SELECT cat_id FROM public.\"Category\" where cat_name =?;";
	
	private CategoryQueries() {
		
	}
}
