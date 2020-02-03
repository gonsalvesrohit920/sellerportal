package com.springau.sellerportal.daoimpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springau.sellerportal.dao.SellerDAO;
import com.springau.sellerportal.model.Documents;
import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.queries.DocumentQueries;
import com.springau.sellerportal.queries.SellerQueries;
import com.springau.sellerportal.rowmapper.DocumentRowMapper;
import com.springau.sellerportal.rowmapper.PendingSellerDetail;
import com.springau.sellerportal.rowmapper.SellerLoginMapper;
import com.springau.sellerportal.rowmapper.SellerMapper;

/**
 * The Class SellerDAOImpl.
 * Implemenation of SellerDAO
 * @author Rohit Gonsalves
 * 	Database interaction for the seller Class
 */
@Repository
public class SellerDAOImpl implements SellerDAO {

	JdbcTemplate jdbcTemplate;

	/**
	 * Instantiates a new seller DAO impl.
	 *
	 * @param dataSource the data source
	 */
	@Autowired
	public SellerDAOImpl(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);

	}

	/**
	 * Gets the seller by email.
	 *
	 * @param email the email
	 * @return the Seller by email
	 */
	@Override
	public Seller getSellerByEmail(String email) {
		
		try {
			Seller seller = jdbcTemplate.queryForObject(
					SellerQueries.GET_SELLER_LOGIN_DATA_BY_EMAIL,
					new Object[] { email.trim() }, 
					new SellerLoginMapper()
					);
		
			if(seller != null) {
				return seller;
			}
			else {
				return new Seller();
			}
		}
		catch (Exception e) {
			Seller seller = new Seller();
			seller.setExists(false);
			return seller;
		}
	}

	
	/**
	 * Save seller to the database.
	 *
	 * @param seller the seller data with documents
	 * @return the Id of the newly inserted Seller
	 */
	@Override
	public int saveSeller(Seller seller) {
		jdbcTemplate.update(SellerQueries.SAVE_SELLER,
				seller.getName(),
				seller.getEmail(),
				seller.getPassword(),
				seller.getContact().getPhoneNo(),
				seller.getContact().getStreet(),
				seller.getContact().getCity(),
				seller.getContact().getPincode(),
				"Pending"
		);
		
		return this.getSellerIdFromEmail(seller.getEmail());
	}
	
	
	

	/**
	 * Gets the Complete seller details by email.
	 *
	 * @param email the email id of the seller 
	 * @return the seller details by email
	 */
	@Override
	public Seller getSellerDetailsByEmail(String email) {
		
		Seller seller = jdbcTemplate.queryForObject(
				SellerQueries.GET_SELLER_LOGIN_DATA_BY_EMAIL,
				new Object[] { email },
				new SellerMapper());
		
		Documents documents = jdbcTemplate.queryForObject(
														DocumentQueries.GET_DOCUMENTS,
														new Object[] { seller.getId() },
														new DocumentRowMapper());
		
		seller.setDocuments(documents);
		return seller;
	}

	@Override
	public Seller updateSeller(int sellerId, Seller seller) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Save seller documents.
	 *
	 * @param documents the documents alongwith the SellerId
	 * @return the documents
	 */
	public Documents saveSellerDocuments(Documents documents) {
		
		jdbcTemplate.update(
				DocumentQueries.SAVE_DOCUMENTS,
				documents.getSellerId(),
				documents.getPanNo(),
				documents.getPanImageType(),
				documents.getPanImage(),
				documents.getGstInNo(),
				documents.getGstInImageType(),
				documents.getPanImage()
				);
		return documents;
	}
	
	public int getSellerIdFromEmail(String email) {
		
		Seller s = this.getSellerByEmail(email);
		return s.getId();
		
	}

	/**
	 * Gets the pending seller details.
	 *
	 * @return the pending seller details
	 * @author Shashank Jain
	 */
	@Override
	public List<Seller> getPendingSellerDetails() {
		List<Seller> pendingSellers = jdbcTemplate.query(SellerQueries.GET_PENDING_DATA_ALL_SELLERS,new PendingSellerDetail());
		System.out.println(pendingSellers);
		return pendingSellers;
	}

}
