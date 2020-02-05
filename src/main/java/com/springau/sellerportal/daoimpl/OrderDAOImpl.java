package com.springau.sellerportal.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springau.sellerportal.dao.OrderDAO;
import com.springau.sellerportal.model.Order;
import com.springau.sellerportal.queries.OrderQueries;
import com.springau.sellerportal.rowmapper.IdMapper;
import com.springau.sellerportal.rowmapper.OrderCountAndSumRowMapper;

@Repository
public class OrderDAOImpl implements OrderDAO {
	
	JdbcTemplate jdbcTemplate;
	@Autowired
	public OrderDAOImpl(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int placeOrder(Order order) {
		List<Integer> qtyList=jdbcTemplate.query(OrderQueries.GET_AVAILABLE_QTY_FROM_PRODUCT, 
				new Object[] {
				order.getProductId(),
				order.getSellerId()
		}, new IdMapper());
		
		List<Integer> total=jdbcTemplate.query(OrderQueries.GET_QTY_OF_PRODUCT_SOLD,new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, order.getSellerId());
				ps.setInt(2, order.getProductId());
				
			}
			
		},new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {				
				return rs.getInt("soldQty");
			}
		});
		if(qtyList.size()==1&&total.size()==1) {
			int currentQuantity = qtyList.get(0) - total.get(0);
			System.out.println("sum="+total.get(0)+"  cq="+currentQuantity);
			if(currentQuantity >= order.getQuantity() && currentQuantity != 0) {
				int orderId=jdbcTemplate.queryForObject(OrderQueries.INSERT_ORDER_DATA,new Object[] {
						order.getCustomerId(),
						order.getSellerId(),
						order.getProductId(),
						order.getQuantity()
						
				},new IdMapper());
				return 1;
			} else {
				return 0;
			}
		}
		else {
			return 0;
		}
	}

	@Override
	public int submitRating(int o_id, int rating) {
		int rowsAffected=jdbcTemplate.update(OrderQueries.UPDATE_RATING_IN_ORDER, rating, o_id);
		if(rowsAffected==0) {
			return 0;
		}
		else {
			List<Integer> productIds=jdbcTemplate.query(OrderQueries.GET_PRODUCT_ID_FROM_ORDER_ID,new Object[] {o_id}, new IdMapper());
			int p_id=productIds.get(0);
			List<List<Integer>> countAndSum=jdbcTemplate.query(OrderQueries.GET_COUNT_AND_SUM_OF_RATING,new Object[] {p_id},new OrderCountAndSumRowMapper());
			int count=countAndSum.get(0).get(0);
			int sum=countAndSum.get(0).get(1);
			int avgRating=sum/count;
			int updatedAvgRatingCount=jdbcTemplate.update(OrderQueries.UPDATE_AVERAGE_RATING_IN_PRODUCT, count,avgRating, p_id);
			System.out.println(p_id);
			System.out.println(count);
			System.out.println(sum);
			System.out.println(avgRating);
			System.out.println(updatedAvgRatingCount);
			return 1;
		}
	}
	
	

}
