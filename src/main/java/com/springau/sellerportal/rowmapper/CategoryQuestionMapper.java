package com.springau.sellerportal.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springau.sellerportal.model.CategoryQuestion;

public class CategoryQuestionMapper implements RowMapper<CategoryQuestion> {

	@Override
	public CategoryQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategoryQuestion categoryquestion = new CategoryQuestion();
		categoryquestion.setCatqId(rs.getInt(2));
		categoryquestion.setCatQuestion(rs.getString(3));
		categoryquestion.setCatId(rs.getInt(1));
		return categoryquestion;
	}

}
