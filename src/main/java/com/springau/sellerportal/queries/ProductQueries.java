package com.springau.sellerportal.queries;

public class ProductQueries {
	
	public static final String ALL_PRODUCTS = "select * from \"Product\"";
	
	public static final String PRODUCT_BY_ID = "select * from \"Product\" where p_id=?";
	
	public static final String PRODUCT_BY_SELLER = "SELECT p_id, p_name, p_description, category, quantity, price" + 
			"	FROM public.\"Product\" where s_id=?;";
	
	public static final String STORE_PRODUCT = "INSERT INTO \"Product\"(" + 
					"	 s_id, p_name, p_description, category, quantity, price)" + 
					"	VALUES (?, ?, ?, ?, ?, ?) RETURNING p_id;";
	public static final String GET_SELLER_PRODUCT_LIST="select * from \"Product\" where s_id=? ";
	
	public static final String UPDATE_PRODUCT_TABLE="update \"Product\" set p_description=?, quantity=?, price=? where p_id=?;  ";
	
	public static final String DELETE_PRODUCT_BY_ID="delete from \"Product\" where p_id=?;";
	
	public static final String DELETE_PRODUCT_SPECIFICATION_BY_ID="delete from \"Category_answers\" where p_id=?";
	
	public static final String DELETE_PRODUCT_IMAGES_BY_ID="delete from \"Product_images\" where p_id=?;";
	
	private ProductQueries() {
		
	}

}
