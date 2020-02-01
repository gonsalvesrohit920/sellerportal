package com.springau.sellerportal.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springau.sellerportal.databasecolumns.DocumentsColumns;
import com.springau.sellerportal.model.Documents;

/**
 * The Class DocumentRowMapper.
 */
/**
 * @author Rohit Gonsalves
 *
 */
public class DocumentRowMapper implements RowMapper<Documents> {

	/**
	 * Deserialize Documents.
	 *
	 * @param rs the ResultSet
	 * @param rowNum the row number
	 * @return the documents
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Documents mapRow(ResultSet rs, int rowNum) throws SQLException {
		Documents documents = new Documents();
		documents.setSellerId(rs.getInt(DocumentsColumns.SELLER_ID));
		documents.setPanNo(rs.getString(DocumentsColumns.PAN_NO));
		documents.setPanImageType(rs.getString(DocumentsColumns.PAN_IMAGE_TYPE));
		documents.setPanImage(rs.getBytes(DocumentsColumns.PAN_IMAGE_DATA));
		documents.setGstInNo(rs.getString(DocumentsColumns.GSTIN_NO));
		documents.setGstInImageType(rs.getString(DocumentsColumns.GSTIN_IMAGE_TYPE));
		documents.setGstInImage(rs.getBytes(DocumentsColumns.GSTIN_IMAGE_DATA));
		return documents;
	}
	
	

}
