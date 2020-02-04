package com.springau.sellerportal.daoimpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;
import com.springau.sellerportal.dao.ProductDAO;
import com.springau.sellerportal.model.CategoryAnswer;
import com.springau.sellerportal.model.CategoryQuestion;
import com.springau.sellerportal.model.Product;
import com.springau.sellerportal.model.ProductImage;
import com.springau.sellerportal.model.QuestionAnswer;
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
	
	
	public List<Product> getSellerProductList(int sellerId) {
		List<Product> productList=jdbcTemplate.query(ProductQueries.GET_SELLER_PRODUCT_LIST,new PreparedStatementSetter() {
			@Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, sellerId);
            }

        }, new ProductMapper());
		productList.forEach((p)->{
		
			List<QuestionAnswer> qa=jdbcTemplate.query(CategoryAnswerQueries.GET_PRODUCT_ANSWERS, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement preparedStatement) throws SQLException {
					preparedStatement.setInt(1, p.getProductId());
					
				}},new CategoryAnswerMapper());
			Map<String,CategoryAnswer> ca=new HashMap<>();
			qa.forEach((i)->{
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
		insertProductAttributes(product.getAttributes());
		return null;
	}

	@Override
	public List<Product> updateProduct(Product product) {
		//TODO
return null;
	}

	@Override
	public void deleteProduct(int productId) {

		jdbcTemplate.update(ProductQueries.DELETE_PRODUCT_SPECIFICATION_BY_ID,productId);
		jdbcTemplate.update(ProductQueries.DELETE_PRODUCT_IMAGES_BY_ID,productId);
		jdbcTemplate.update(ProductQueries.DELETE_PRODUCT_BY_ID,productId);
		
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
	@Override
	public List<CategoryQuestion> getAllProductAttributes(String categoryname) {
		
		List<Integer> catid = jdbcTemplate.query(CategoryQueries.GET_CATEGORY_ID,new PreparedStatementSetter() {
			@Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, categoryname);
            }

        },new CategoryMapper());
		
        int CatId = catid.get(0);
        List<CategoryQuestion> categoryQueslist = jdbcTemplate.query(CategoryQuestionsQueries.GET_CATEGORY_FIELDS,new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, CatId);
            }
        },new CategoryQuestionMapper());
        System.out.println(categoryQueslist.get(0).getCatQuestion());
		return categoryQueslist;
	}

	@Override
	public void updateProductData(Product product) {


		System.out.println("start");
		jdbcTemplate.update(ProductQueries.UPDATE_PRODUCT_TABLE,product.getDecription(),product.getQuantity(),product.getPrice(),product.getProductId());
		List<CategoryQuestion> categoryQueslist = jdbcTemplate.query(CategoryQuestionsQueries.GET_CATEGORY_FIELDS,new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, product.getCategory());
            }
        },new CategoryQuestionMapper());
		
		System.out.println(categoryQueslist.size()+","+product.getCategory());
		Map<Integer,CategoryAnswer> quetionIdCategory=new HashMap<>();
		categoryQueslist.forEach((cq)->{
			System.out.println(cq.getCatId()+","+cq.getCatQuestion()+","+cq.getCatqId());
			quetionIdCategory.put(cq.getCatqId(), product.getQuestionAnswers().get(cq.getCatQuestion()));
			
		});
		System.out.println(insertUpdatedCategoryAnswer(quetionIdCategory, product));
//		Map<Integer,CategoryAnswer> quetionIdCategory=new HashMap<>();
//		
//		categoryQueslist.forEach((attr)->{
//			System.out.println(attr.getCatId()+","+attr.getCatqId()+","+attr.getCatQuestion());
////			//quetionIdCategory.put(attr.getCatqId(),attr.);
////			
////			quetionIdCategory.put(attr.getCatqId(), null);
//		});
//		System.out.println("attr"+quetionIdCategory);
//		System.out.println(insertUpdatedCategoryAnswer(quetionIdCategory, product));
//		System.out.println("done");
	}
	private boolean insertUpdatedCategoryAnswer(Map<Integer,CategoryAnswer> updatedAnswers,Product product) {

		try {
			for(Map.Entry<Integer, CategoryAnswer> entry:updatedAnswers.entrySet()) {
				CategoryAnswer categoryAnswer=entry.getValue();
				System.out.println(categoryAnswer.getCatId()+","+categoryAnswer.getCatAnswer()+","+product.getProductId()+","+entry.getKey());
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
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String chechStatus() {
		String response = jdbcTemplate.queryForObject(SellerQueries.GET_SELLER_Status,new Object[] { 1 }, String.class);
		return response;
	}

	@Override
	public void updateStatus(int sellerId) {
		jdbcTemplate.update(SellerQueries.UPDATE_Status,sellerId);
		jdbcTemplate.update(SellerQueries.DELETE_SELLER_ADMIN,sellerId);
	}
	
}
