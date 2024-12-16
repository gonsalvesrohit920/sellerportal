package com.springau.sellerportal.queries;

public class DocumentQueries {

	public static final String GET_DOCUMENTS = "SELECT s_id,pan_no, pan_image_type, pan_image, gst_in_no, gst_in_image_type, gst_in_image\r\n" + 
			"	FROM public.\"Documents\" where s_id=?;";
	
	
	public static final String SAVE_DOCUMENTS = "INSERT INTO public.\"Documents\"(\r\n" + 
			"	s_id, pan_no, pan_image_type, pan_image, gst_in_no, gst_in_image_type, gst_in_image)\r\n" + 
			"	VALUES (?, ?, ?, ?, ?, ?, ?);";
	
	
	private DocumentQueries() {
		
	}
}
