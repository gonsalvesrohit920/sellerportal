package com.springau.sellerportal.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CategoryID  implements RowMapper<Integer> { 
	@Override
	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return rs.getInt(1);
	} 

}
