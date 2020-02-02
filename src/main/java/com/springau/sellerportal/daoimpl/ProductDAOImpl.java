package com.springau.sellerportal.daoimpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springau.sellerportal.dao.ProductDAO;
import com.springau.sellerportal.model.CategoryAnswer;
import com.springau.sellerportal.model.Product;
import com.springau.sellerportal.model.ProductImage;
import com.springau.sellerportal.queries.CategoryAnswerQueries;
import com.springau.sellerportal.queries.ProductImageQueries;
import com.springau.sellerportal.queries.ProductQueries;
import com.springau.sellerportal.rowmapper.IdMapper;
import com.springau.sellerportal.rowmapper.ProductImageMapper;
import com.springau.sellerportal.rowmapper.ProductMapper;


/**
 * @author Rohit Gonsalves
 *	Database interfacing for Products
 */
@Repository
public class ProductDAOImpl implements ProductDAO{
	
	
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ProductDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Gets the all products.
	 *
	 * @return the all products
	 */
	@Override
	public List<Product> getAllProducts() { 
		
		return jdbcTemplate.query(ProductQueries.ALL_PRODUCTS, new ProductMapper());
	}
	
	/**
	 * Gets the all products for a particular seller.
	 * @author Rohit Gonsalves
	 * @param sellerId the seller id
	 * @return the all seller products
	 */
	@Override
	public List<Product> getAllSellerProducts(int sellerId){
		return jdbcTemplate.query(ProductQueries.ALL_PRODUCTS,new Object[] { sellerId }, new ProductMapper());
	}

	@Override
	public List<Product> saveProduct(Product product) {
		int productId = jdbcTemplate.queryForObject(
				ProductQueries.STORE_PRODUCT,
				new Object[] {
				product.getSellerId(),
				product.getName(),
				product.getDecription(),
				product.getCategory(),
				product.getQuantity(),
				product.getPrice()},
				new IdMapper()
				);
		
		product.setProductId(productId);
		
		for(CategoryAnswer catAnswer : product.getAttributes()) {
			catAnswer.setProductId(productId);
		}
		return null;
	}

	@Override
	public List<Product> updateProduct(Product product) {
		//TODO
		return null;
	}

	@Override
	public List<Product> deleteProduct(int productId) {
		
		return null;
	}

	@Override
	public List<CategoryAnswer> getProductAttributes(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the product images by product Id.
	 *
	 * @param productId the product id
	 * @return the product images
	 */
	@Override
	public List<ProductImage> getProductImages(int productId) {
		
		
		return jdbcTemplate.query(
				ProductImageQueries.GET_PRODUCT_IMAGES,
				new Object[] { 
						productId 
						},
				new ProductImageMapper());
	}
	
	
	public boolean insertProductAttributes(List<CategoryAnswer> answers) {
		
		try {
			for(CategoryAnswer categoryAnswer : answers) {
				jdbcTemplate.update(
						CategoryAnswerQueries.SAVE_PRODUCT_ANSWERS,
						categoryAnswer.getProductId(),
						categoryAnswer.getCatId(),
						categoryAnswer.getCatqId(),
						categoryAnswer.getCatAnswer()
						);
			}
			
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public List<ProductImage> saveProductImages(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
