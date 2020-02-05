package com.springau.sellerportal.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springau.sellerportal.databasecolumns.ProductColumns;
import com.springau.sellerportal.model.Product;

public class ProductMapper implements RowMapper<Product>{

	/**
	 * Map row.
	 * @author Rohit
	 * @param rs the ResultSet
	 * @param rowNum the Row Number
	 * @return Product
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Product product = new Product();
		
		product.setProductId(rs.getInt(ProductColumns.PRODUCT_ID));
		product.setSellerId(rs.getInt(ProductColumns.SELLER_ID));
		product.setName(rs.getString(ProductColumns.PRODUCT_NAME));
		product.setDecription(rs.getString(ProductColumns.DESCRIPTION));
		product.setCategory(rs.getInt(ProductColumns.CATEGORY));
		product.setQuantity(rs.getInt(ProductColumns.QUANTITY));
		product.setPrice(rs.getInt(ProductColumns.PRICE));
		product.setRating(rs.getInt("avg_rating"));
		return product;
	}

}
