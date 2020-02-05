package com.springau.sellerportal.queries;

public class OrderQueries {
	
	public static final String GET_AVAILABLE_QTY_FROM_PRODUCT="select quantity from public.\"Product\" where p_id=? and s_id=?;";
	
	public static final String GET_QTY_OF_PRODUCT_SOLD="select sum(quantity) as soldQty from public.\"Order\" where s_id=? and p_id=?;";
	
	public static final String INSERT_ORDER_DATA="INSERT INTO public.\"Order\"(" + 
			" c_id, s_id, p_id, quantity)" + 
			"	VALUES ( ?, ?, ?, ?) returning o_id;";
	
	public static final String UPDATE_RATING_IN_ORDER="UPDATE public.\"Order\"" + 
			"	SET  rating=?" + 
			"	WHERE o_id=? and rating=0;";
	
	public static final String GET_COUNT_AND_SUM_OF_RATING = "SELECT count(rating), sum(rating)" + 
			"	FROM public.\"Order\"" + 
			"	WHERE rating != 0 AND p_id = ?;";
	public static final String GET_PRODUCT_ID_FROM_ORDER_ID="select p_id from public.\"Order\" "
			+ "where o_id=?;";
	
	public static final String UPDATE_AVERAGE_RATING_IN_PRODUCT = "UPDATE public.\"Product\"" + 
			"	SET  rating_count=?, avg_rating=?" + 
			"	WHERE p_id=?;";
	private OrderQueries() {
		
	}

}
