package com.springau.sellerportal.daoimpl;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springau.sellerportal.dao.ProductDAO;
import com.springau.sellerportal.model.Product;
import com.springau.sellerportal.model.ProductImage;
import com.springau.sellerportal.queries.ProductQueries;
import com.springau.sellerportal.rowmapper.ProductMapper;


@Repository
public class ProductDAOImpl implements ProductDAO{
	
	
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ProductDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Product> getAllProducts() {

		List<Product> product = jdbcTemplate.query(ProductQueries.ALL_PRODUCTS, new ProductMapper());
		
		/*List<Product> products = new ArrayList<>();
		for(Map row : rows) {
			
		}
		*/
		
		return product;
	}

	@Override
	public List<Product> saveProduct(Product product) {

		return null;
	}

	@Override
	public List<Product> updateProduct(Product product) {

		return null;
	}

	@Override
	public List<Product> deleteProduct(int productId) {
		
		return null;
	}

	@Override
	public Map<String, String> getProductAttributes(int productId) {
		return null;
	}

	@Override
	public List<ProductImage> getProductImages(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
