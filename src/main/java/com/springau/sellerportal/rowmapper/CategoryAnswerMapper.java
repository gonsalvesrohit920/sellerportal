package com.springau.sellerportal.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.springau.sellerportal.model.CategoryAnswer;
import com.springau.sellerportal.model.CategoryQuestion;
import com.springau.sellerportal.model.QuestionAnswer;

public class CategoryAnswerMapper implements RowMapper<QuestionAnswer> {

	@Override
	public QuestionAnswer mapRow(ResultSet rs, int rowNum) throws SQLException {
		HashMap<String,CategoryAnswer> dummy=new HashMap<String, CategoryAnswer>();
		QuestionAnswer questionAnswer=new QuestionAnswer();
		questionAnswer.setCatId(rs.getInt("cat_id"));
		questionAnswer.setCatqId(rs.getInt("catq_id"));
		questionAnswer.setProductId(rs.getInt("p_id"));
		questionAnswer.setCatAnswer(rs.getString("cat_answer"));
		questionAnswer.setQuestion(rs.getString("cat_question"));
		return questionAnswer;
	}

}
