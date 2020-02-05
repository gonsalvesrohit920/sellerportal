package com.springau.sellerportal.queries;

/**
 * @author Rohit Gonsalves
 * Queries for Documents Table
 */
public class DocumentQueries {

	public static final String GET_DOCUMENTS = "SELECT s_id,pan_no, pan_image_type, pan_image, gst_in_no, gst_in_image_type, gst_in_image" + 
			"	FROM public.\"Documents\" where s_id=?;";
	
	
	public static final String SAVE_DOCUMENTS = "INSERT INTO \"Documents\"(" + 
			"	s_id, pan_no, pan_image_type, pan_image, gst_in_no, gst_in_image_type, gst_in_image)" + 
			"	VALUES (?, ?, ?, ?, ?, ?, ?);";
	public static final String SAVE_PAN_IMAGE = "UPDATE \"Documents\"" + 
			"	SET pan_image=?" + 
			"	WHERE s_id =? ;";
	
	
	public static final String SAVE_GSTIN_IMAGE = "UPDATE \"Documents\"" + 
			"	SET gst_in_image=?" + 
			"	WHERE s_id =? ;";
	
	public static final String GET_SELLER_IMAGES = "SELECT s_id, pan_no, pan_image_type, pan_image, gst_in_no, gst_in_image_type, gst_in_image" + 
			"	FROM \"Documents\" where s_id=?;";
	
	private DocumentQueries() {
		
	}
}
