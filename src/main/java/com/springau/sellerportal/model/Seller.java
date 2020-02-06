package com.springau.sellerportal.model;



import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class Seller.
 *
 * @author Rohit Gonsalves
 */

/**
 * Gets the documents.
 *
 * @return the documents
 */
@Getter

/**
 * Sets the documents.
 *
 * @param documents the new documents
 */
@Setter

/**
 * Instantiates a new seller.
 */
@NoArgsConstructor

/**
 * Instantiates a new seller.
 *
 * @param id the id
 * @param name the name
 * @param email the email
 * @param password the password
 * @param contact the contact
 * @param applicationStatus the application status
 * @param isValid the is valid
 * @param exists the exists
 * @param documents the documents
 */

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString
public class Seller {
	
	/** The id. */
	private int id;
	
	/** The name. */
	private String name;
	
	/** The email. */
	private String email;
	
	/** The password. */
	private String password;
	
	/** The contact. */
	private Contact contact;
	
	/** The application status. */
	private String applicationStatus;
	
	/** The is valid. */
	private boolean isValid = true;
	
	/** The exists. */
	private boolean exists = true;
	
	/** The documents. */
	private Documents documents;
	
	private List<String> category; 

}
