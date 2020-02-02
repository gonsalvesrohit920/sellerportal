package com.springau.sellerportal.queries;

public class ProductQueries {
	
	public static final String ALL_PRODUCTS = "select * from \"Product\"";
	
	public static final String PRODUCT_BY_ID = "select * from \"Product\" where p_id=?";
	
	public static final String PRODUCT_BY_SELLER = "SELECT p_id, p_name, p_description, category, quantity, price" + 
			"	FROM public.\"Product\" where s_id=?;";
	
	public static final String STORE_PRODUCT = "INSERT INTO \"Product\"(" + 
					"	 s_id, p_name, p_description, category, quantity, price)" + 
					"	VALUES (?, ?, ?, ?, ?, ?) RETURNS p_id;";
	
	private ProductQueries() {
		
	}

}
