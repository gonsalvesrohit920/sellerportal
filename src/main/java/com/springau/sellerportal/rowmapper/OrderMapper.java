package com.springau.sellerportal.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springau.sellerportal.model.OrderData;

public class OrderMapper implements RowMapper<OrderData>{

	@Override
	public OrderData mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		OrderData orderData=new OrderData();
		orderData.setProductId(rs.getInt("p_id"));
		orderData.setTotalQty(rs.getInt("sum"));
		return orderData;
	} 

}
