package com.springau.sellerportal.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


/**
 * The Class IdMapper.
 *
 * @author Rohit Gonsalves
 */
public class IdMapper implements RowMapper<Integer> {
	
	/**
	 * Map row.
	 *
	 * @param rs the ResultSet
	 * @param rowNum the row number
	 * @return the Id
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return rs.getInt(1);
		
	}
	
	

}
