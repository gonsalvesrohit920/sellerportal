package com.springau.sellerportal.queries;

public class ProductImageQueries {
	
	public static final String SAVE_IMAGE = "INSERT INTO \"Product_images\"(" + 
												"	p_id, p_image_type, image_file)" + 
													"	VALUES (?, ?, ?);";
	
	public static final String GET_ALL_IMAGES = "SELECT p_id, p_image_type, image_file, pi_id" + 
													"	FROM \"Product_images\";";			
	
	public static final String GET_PRODUCT_IMAGES = "SELECT p_id, p_image_type, image_file, pi_id" + 
													"	FROM \"Product_images\" where pi_id=?;";
	
	private ProductImageQueries() {
		
	}

}
