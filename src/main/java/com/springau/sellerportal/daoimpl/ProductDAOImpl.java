package com.springau.sellerportal.daoimpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.springau.sellerportal.dao.ProductDAO;
import com.springau.sellerportal.model.CategoryAnswer;
import com.springau.sellerportal.model.CategoryQuestion;
import com.springau.sellerportal.model.Product;
import com.springau.sellerportal.model.ProductImage;
import com.springau.sellerportal.model.QuestionAnswer;
import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.queries.CategoryAnswerQueries;
import com.springau.sellerportal.queries.CategoryQueries;
import com.springau.sellerportal.queries.CategoryQuestionsQueries;
import com.springau.sellerportal.queries.ProductImageQueries;
import com.springau.sellerportal.queries.ProductQueries;
import com.springau.sellerportal.queries.SellerQueries;
import com.springau.sellerportal.rowmapper.CategoryAnswerMapper;
import com.springau.sellerportal.rowmapper.CategoryMapper;
import com.springau.sellerportal.rowmapper.CategoryQuestionMapper;
import com.springau.sellerportal.rowmapper.IdMapper;
import com.springau.sellerportal.rowmapper.ProductImageMapper;
import com.springau.sellerportal.rowmapper.ProductMapper;
import com.springau.sellerportal.rowmapper.SellerMapper;
import com.springau.sellerportal.service.MailService;


/**
 * @author Rohit Gonsalves
 *	Database interfacing for Products
 */
@Repository
public class ProductDAOImpl implements ProductDAO{
	
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private MailService mailservice;
	
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
	
	
	public List<Product> getSellerProductList(int sellerId) {
		List<Product> productList=jdbcTemplate.query(ProductQueries.GET_SELLER_PRODUCT_LIST,new PreparedStatementSetter() {
			@Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, sellerId);
            }

        }, new ProductMapper());
		productList.forEach( p -> {
		
			List<QuestionAnswer> qa=jdbcTemplate.query(CategoryAnswerQueries.GET_PRODUCT_ANSWERS, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement preparedStatement) throws SQLException {
					preparedStatement.setInt(1, p.getProductId());
					
				}},new CategoryAnswerMapper());
			Map<String,CategoryAnswer> ca=new HashMap<>();
			qa.forEach( i ->{
				CategoryAnswer c=new CategoryAnswer();
				c.setCatAnswer(i.getCatAnswer());
				c.setCatId(i.getCatId());
				c.setCatqId(i.getCatqId());
				c.setProductId(i.getProductId());
				
				ca.put(i.getQuestion(),c);
			});
			p.setQuestionAnswers(ca);
		});
		return productList;
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
	public List<Integer> saveProduct(Product product) {
		List<Integer> productImageIds = new ArrayList<>();
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
		insertProductAttributes(product.getAttributes());
		
		for(ProductImage image: product.getImages())
		{
			productImageIds.add(storeImageMetadata(image, productId));
		}
		return productImageIds;
	}

	

	@Override
	public int deleteProduct(int productId) {
		
		List<Integer> qtyList=jdbcTemplate.query(ProductQueries.GET_AVAILABLE_QTY_FROM_PRODUCT, new Object[] {
				productId
		},new IdMapper());
		
		if(qtyList!=null&&qtyList.size()==1) {
			int qty=qtyList.get(0);
			
			List<Integer> total=jdbcTemplate.query(ProductQueries.GET_QTY_OF_PRODUCT_SOLD,new Object[] {
					productId
			},new IdMapper());
			int soldQty=total.get(0);
			
			if(soldQty!=0&&soldQty<qty*0.3) {
				
				jdbcTemplate.update(ProductQueries.PRODUCT_SOFT_DELETE, productId);
				return productId;
			}
			else {

				
				Product product=getProductById(productId);
				int sellerId=getSellerIdByProductId(productId);
				Seller seller=getSellerDataFromSelleId(sellerId);
				
				mailservice.sendDeleteWarning(seller, product, qty, soldQty);
				return 0;
			}
			
		}
		else {
			
		}
		return -1;
		
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
				ProductImageQueries.GET_PRODUCT_IMAGE_DATA,
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
			
			return false;
		}
		
		
	}

	
	@Override
	public List<CategoryQuestion> getAllProductAttributes(String categoryname) {
		
		List<Integer> catid = jdbcTemplate.query(CategoryQueries.GET_CATEGORY_ID,new PreparedStatementSetter() {
			@Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, categoryname);
            }

        },new CategoryMapper());
		
        int catId = catid.get(0);
        return jdbcTemplate.query(CategoryQuestionsQueries.GET_CATEGORY_FIELDS,new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, catId);
            }
        },new CategoryQuestionMapper());
        
		
	}

	@Override
	public int updateProductData(Product product) {


		
		try {
			jdbcTemplate.update(ProductQueries.UPDATE_PRODUCT_TABLE,product.getDecription(),product.getQuantity(),product.getPrice(),product.getProductId());
			List<CategoryQuestion> categoryQueslist = jdbcTemplate.query(CategoryQuestionsQueries.GET_CATEGORY_FIELDS,new PreparedStatementSetter() {
	            public void setValues(PreparedStatement preparedStatement) throws SQLException {
	                preparedStatement.setInt(1, product.getCategory());
	            }
	        },new CategoryQuestionMapper());
			
			
			Map<Integer,CategoryAnswer> quetionIdCategory=new HashMap<>();
			categoryQueslist.forEach( cq -> {
				
				quetionIdCategory.put(cq.getCatqId(), product.getQuestionAnswers().get(cq.getCatQuestion()));
				
			});
			
			return 1;
		}catch (Exception e) {
			
		}
		return 0;
		
	}
	private boolean insertUpdatedCategoryAnswer(Map<Integer,CategoryAnswer> updatedAnswers,Product product) {

		try {
			for(Map.Entry<Integer, CategoryAnswer> entry:updatedAnswers.entrySet()) {
				CategoryAnswer categoryAnswer=entry.getValue();
				
				jdbcTemplate.update(
						CategoryAnswerQueries.UPDATE_PRODUCT_ANSWER,					
						categoryAnswer.getCatAnswer(),
						product.getProductId(),
						categoryAnswer.getCatId(),
						entry.getKey()
						);
			}
			
			return true;
		}
		catch (Exception e) {
			
			return false;
		}
	}

	@Override
	public String chechStatus(int sellerId) {
		return jdbcTemplate.queryForObject(SellerQueries.GET_SELLER_Status,new Object[] { sellerId }, String.class);
	}

	@Override
	public void updateStatus(int sellerId) {
		jdbcTemplate.update(SellerQueries.UPDATE_Status,sellerId);
		jdbcTemplate.update(SellerQueries.DELETE_SELLER_ADMIN,sellerId);
	}
	
	
	@Override
	public Product getProductById(int productId) {
		return jdbcTemplate.queryForObject(ProductQueries.PRODUCT_BY_ID, new Object[] {
				productId
		}, new ProductMapper());
	}
	public int getSellerIdByProductId(int productId) {
		return jdbcTemplate.queryForObject(SellerQueries.GET_SELLER_ID_FROM_PRODUCT_ID, new Object[] {
				productId
		}, new IdMapper());
	}
	public Seller getSellerDataFromSelleId(int sellerId) {
		
		return jdbcTemplate.queryForObject(SellerQueries.GET_SELLER_DATA_FROM_SELLER_ID, new Object[] {
				sellerId
		}, new SellerMapper());
		
	}
	public int storeImageMetadata(ProductImage image, int productId) {
		try {
				
				return jdbcTemplate.queryForObject(
						ProductImageQueries.SAVE_IMAGE_METADATA,
						new Object[] { 
								
								productId,
								image.getImageType(),
								
						}
						, new IdMapper());
			}
		catch (Exception e) {
			
			return -1;
		}
	
}
	
	@Override
	public boolean saveProductImages(List<Integer> productImageIds, List<byte[]> productImages) {
		
		boolean response;
		
		try {
			for(int i = 0; i < productImageIds.size(); i++) {
				jdbcTemplate.update(ProductImageQueries.SAVE_PRODUCT_IMAGE, 
						productImages.get(i), 
						productImageIds.get(i));
			}
			response = true;
		}
		catch (Exception e) {
			
			response = false;
		}
		
		
		return response;
	}
	
}
