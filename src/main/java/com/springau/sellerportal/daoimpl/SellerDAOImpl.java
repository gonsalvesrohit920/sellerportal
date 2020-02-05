package com.springau.sellerportal.daoimpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.springau.sellerportal.dao.SellerDAO;
import com.springau.sellerportal.model.Documents;
import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.queries.CategoryQueries;
import com.springau.sellerportal.queries.CategoryQuestionsQueries;
import com.springau.sellerportal.queries.DocumentQueries;
import com.springau.sellerportal.queries.SellerQueries;
import com.springau.sellerportal.rowmapper.CategoryID;
import com.springau.sellerportal.rowmapper.DocumentRowMapper;
import com.springau.sellerportal.rowmapper.PendingSellerDetail;
import com.springau.sellerportal.rowmapper.SellerLoginMapper;
import com.springau.sellerportal.rowmapper.SellerMapper;

/**
 * The Class SellerDAOImpl. Implemenation of SellerDAO
 * 
 * @author Rohit Gonsalves Database interaction for the seller Class
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
			Seller seller = jdbcTemplate.queryForObject(SellerQueries.GET_SELLER_LOGIN_DATA_BY_EMAIL,
					new Object[] { email.trim() }, new SellerLoginMapper());

			if (seller != null) {
				return seller;
			} else {
				return new Seller();
			}
		} catch (Exception e) {
			Seller seller = new Seller();
			seller.setExists(false);
			seller.setValid(false);
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
		
		int sellerId = this.getSellerIdFromEmail(seller.getEmail());
		
		for(String s:seller.getCategory()) {
			int categoryId =  jdbcTemplate.queryForObject(CategoryQueries.GET_CATEGORY_ID,new Object[] { s },Integer.class);
			jdbcTemplate.update(SellerQueries.SAVE_CATEGORY, sellerId,categoryId);
		}
		//List<Integer> categoryIdlist = jdbcTemplate.query(CategoryQueries.GET_CATEGORY_ID,)
		return sellerId;
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
				new Object[] { email }, new SellerMapper()
				);
		

		Documents documents = jdbcTemplate.queryForObject(
				DocumentQueries.GET_DOCUMENTS,
				new Object[] { seller.getId() }, new DocumentRowMapper()
				);

		seller.setDocuments(documents);
		return seller;
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

	@Override
	public List<String> getCategory(int sid) {
	    List<String> category = new ArrayList<String>();
		List<Integer> categorylist = jdbcTemplate.query(CategoryQuestionsQueries.GET_CATEGORY_ID,new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, sid);
            }
        }, new CategoryID());
		for(int id :categorylist) {
			String s = jdbcTemplate.queryForObject(CategoryQuestionsQueries.GET_CATEGORYS,new Object[] { id },String.class);
			category.add(s);
		}
		return category;
	}
	
	@Override
	public boolean savePanImage(int sellerId, byte[] panImage) {
		
		jdbcTemplate.update(DocumentQueries.SAVE_PAN_IMAGE, panImage, sellerId);
		
		return true;
	}
	
	
	@Override
	public boolean saveGstinImage(int sellerId, byte[] gstinImage) {
		
		System.out.println(gstinImage.length);
		
		jdbcTemplate.update(DocumentQueries.SAVE_GSTIN_IMAGE, gstinImage, sellerId);
		
		return true;
	}

	@Override
	public Documents getPanImage(int sellerId) {
		
		return jdbcTemplate.queryForObject(
				DocumentQueries.GET_DOCUMENTS,
				new Object[] { sellerId }, new DocumentRowMapper()
				);
		
		
	}

	

}
