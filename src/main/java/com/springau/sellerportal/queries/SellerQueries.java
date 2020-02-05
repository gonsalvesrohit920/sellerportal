package com.springau.sellerportal.queries;

/**
 * The Class SellerQueries for queries on seller tabler. 
 *
 * @author Rohit Gonsalves
 */
public class SellerQueries {
	
	public static final String GET_COMPLETE_SELLER_DATA_BYEMAIL = "SELECT d.s_id,s.s_name, s.email, s.s_password, s.phone_no,+"
																	+ " s.street, s.city, s.pincode, s.application_status,+"
																	+ " d.pan_no, d.pan_image_type, d.pan_image, d.gst_in_no, d.gst_in_image_type, d.gst_in_image" + 
																	"	FROM public.\"Documents\" as d, \"Seller\" as s where s.email = ? and s.s_id = d.s_id;";
	
	public static final String GET_COMPLETE_DATA_ALL_SELLERS = "SELECT d.s_id,s.s_name, s.email, s.s_password, s.phone_no," 
															  + " s.street, s.city, s.pincode, s.application_status,"
															  + " d.pan_no, d.pan_image_type, d.pan_image, d.gst_in_no, d.gst_in_image_type, d.gst_in_image" + 
															  "	FROM public.\"Documents\" as d, \"Seller\" as s where and s.s_id = d.s_id;";
	
	public static final String GET_SELLER_LOGIN_DATA_BY_EMAIL= "select * from \"Seller\" where email=?";
	
	public static final String SAVE_SELLER = "INSERT INTO public.\"Seller\"(" + 
											 "	s_name, email, s_password, phone_no, street, city, pincode, application_status)" + 
											 "	VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String GET_SELLER_Status = "SELECT  s.application_status FROM public.\"Seller\" as s where s.s_id =?;";
	
	public static final String UPDATE_Status="update \"Seller\" set application_status='Accepted' where s_id=?;  ";
	
	public static final String DELETE_SELLER_ADMIN="delete from \"Admin_seller\" where s_id=?;";
	public static final String GET_PENDING_DATA_ALL_SELLERS = "SELECT d.s_id,s.s_name, s.email, s.phone_no," 
			  + " s.street, s.city, s.pincode, s.application_status,"
			  + " d.pan_no, d.pan_image_type, d.pan_image, d.gst_in_no, d.gst_in_image_type, d.gst_in_image" + 
			  "	FROM public.\"Documents\" as d, \"Seller\" as s where s.application_status = 'Pending' and s.s_id = d.s_id;";
	
	public static final String GET_SELLER_ID_FROM_PRODUCT_ID="select s_id from \"Product\" where p_id=?";
	
	public static final String GET_SELLER_DATA_FROM_SELLER_ID="select * from public.\"Seller\" where s_id=?;";
	
	public static final String SAVE_CATEGORY = "INSERT INTO public.\"Seller_category\"(" + 
			 "	s_id, c_id)" + 
			 "	VALUES (?, ?);";
	private SellerQueries() {
		
	}

}
