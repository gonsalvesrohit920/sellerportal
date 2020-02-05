package com.springau.sellerportal.queries;

public class ProductImageQueries {
	
	public static final String SAVE_IMAGE = "INSERT INTO \"Product_images\"(" + 
												"	p_id, p_image_type, image_file)" + 
													"	VALUES (?, ?, ?);";
	
	public static final String GET_ALL_IMAGES = "SELECT p_id, p_image_type, image_file, pi_id" + 
													"	FROM \"Product_images\";";			
	
	public static final String GET_PRODUCT_IMAGES = "SELECT p_id, p_image_type, image_file, pi_id" + 
													"	FROM \"Product_images\" where pi_id=?;";
	
	public static final String SAVE_IMAGE_METADATA = "INSERT INTO \"Product_images\"(" + 
			"	 p_id, p_image_type)" + 
					"	VALUES (?, ?) returning pi_id;";
	
	public static final String SAVE_PRODUCT_IMAGE = "UPDATE public.\"Product_images\"" + 
			"	SET image_file=?" + 
			"	WHERE pi_id=?;";
	
	public static final String GET_PRODUCT_IMAGE_DATA = "SELECT pi_id, p_image_type, image_file" + 
														"	FROM \"Product_images\" where p_id = ?;";			

	
	private ProductImageQueries() {
		
	}

}
