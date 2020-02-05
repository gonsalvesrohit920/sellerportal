package com.springau.sellerportal.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.springau.sellerportal.model.Contact;
import com.springau.sellerportal.model.Seller;

/**
 * The Class SellerMapper.
 *
 * @author Rohit Gonsalves
 */
public class SellerMapper implements RowMapper<Seller> {

	/**
	 * Map row.
	 *
	 * @param rs the rs
	 * @param rowNum the row num
	 * @return the seller
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Seller mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Seller seller = new Seller();
		seller.setId(rs.getInt("s_id"));
		seller.setName(rs.getString("s_name"));
		seller.setEmail(rs.getString("email"));
		seller.setPassword(rs.getString("s_password"));
		Contact contact = new Contact();
		
		contact.setStreet(rs.getString("street"));
		contact.setCity(rs.getString("city"));
		contact.setPhoneNo(rs.getString("phone_no"));
		contact.setPincode(rs.getInt("pincode"));
		seller.setContact(contact);
		
		seller.setApplicationStatus(rs.getString("application_status"));
		return seller;
	}

	

}
