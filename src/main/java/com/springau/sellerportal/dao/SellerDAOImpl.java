package com.springau.sellerportal.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.rowmapper.SellerMapper;

@Repository
public class SellerDAOImpl implements SellerDAO {

	JdbcTemplate jdbcTemplate;

	private static final String GET_BY_EMAIL = "select * from \"Seller\" where email=?";

	@Autowired
	public SellerDAOImpl(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);

	}

	@Override
	public Seller getSellerByEmail(String email) {
		
		try {
			Seller seller = jdbcTemplate.queryForObject(GET_BY_EMAIL, new Object[] { email.trim() }, new SellerMapper());
		
			if(seller != null) {
				return seller;
			}
			else {
				return new Seller();
			}
		}
		catch (Exception e) {
			throw e;
		}
	}

}
