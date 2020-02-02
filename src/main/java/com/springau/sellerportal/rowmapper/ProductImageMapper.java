package com.springau.sellerportal.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springau.sellerportal.databasecolumns.ProductImageColumns;
import com.springau.sellerportal.model.ProductImage;

/**
 * The Class ProductImageMapper.
 *
 * @author Rohit Gonsalves
 */
public class ProductImageMapper implements RowMapper<ProductImage>{

	/**
	 * Map row of ProductImage.
	 *
	 * @param rs the rs
	 * @param rowNum the row num
	 * @return the product image
	 * @throws SQLException the SQL exception
	 */
	@Override
	public ProductImage mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ProductImage image = new ProductImage();
		
		image.setImageId(rs.getInt(ProductImageColumns.IMAGE_ID));
		image.setImageType(rs.getString(ProductImageColumns.IMAGE_TYPE));
		image.setProductId(rs.getInt(ProductImageColumns.PRODUCT_ID));
		image.setProductImageData(rs.getBytes(ProductImageColumns.IMAGE_DATA));
		
		
		return image;
	}
	
	

}
