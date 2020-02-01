package com.springau.sellerportal.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springau.sellerportal.databasecolumns.SellerColumns;
import com.springau.sellerportal.model.Seller;

/**
 * @author Rohit Gonsalves
 * Seller Mapper just to get the login data
 */
public class SellerLoginMapper implements RowMapper<Seller> {

	/**
	 * Map row Seller.
	 *Only Extracts important data related to login information 
	 * @param rs the REsultSet 
	 * @param rowNum the row num
	 * @return the seller
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Seller mapRow(ResultSet rs, int rowNum) throws SQLException {
		Seller seller = new Seller();
		seller.setApplicationStatus(rs.getString(SellerColumns.SELLER_APPLICATION_STATUS));
		seller.setId(rs.getInt(SellerColumns.SELLER_ID));
		seller.setEmail(rs.getString(SellerColumns.SELLER_EMAIL));
		seller.setPassword(rs.getString(SellerColumns.SELLER_PASSWORD));
		
		return seller;
	}

}
