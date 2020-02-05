package com.springau.sellerportal.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class OrderCountAndSumRowMapper implements RowMapper<List<Integer>> {

	@Override
	public List<Integer> mapRow(ResultSet rs, int rowNum) throws SQLException {
		List<Integer> countAndSum=new ArrayList<Integer>();
		countAndSum.add(rs.getInt(1));
		countAndSum.add(rs.getInt(2));
		
		return countAndSum;
	}

}
