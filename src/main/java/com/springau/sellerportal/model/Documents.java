package com.springau.sellerportal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The Class Documents.
 *
 * @author Rohit Gonsalves
 */

/**
 * Gets the gst in image.
 *
 * @return the gst in image
 */
@Getter

/**
 * Sets the gst in image.
 *
 * @param gstInImage the new gst in image
 */
@Setter

/**
 * Instantiates a new documents.
 *
 * @param sellerId the seller id
 * @param panNo the pan no
 * @param gstInNo the gst in no
 * @param panImageType the pan image type
 * @param gstInImageType the gst in image type
 * @param panImage the pan image
 * @param gstInImage the gst in image
 */

/**
 * Instantiates a new documents.
 */
@NoArgsConstructor
public class Documents {
	
	/** The seller id. */
	private int sellerId;
	
	/** The pan no. */
	private String panNo;
	
	/** The gst in no. */
	private String gstInNo;
	
	/** The pan card image type. */
	private String panImageType;
	
	/** The gst in image type. */
	private String gstInImageType;
	
	/** The pan image. */
	private byte[] panImage;
	
	/** The gst in image. */
	private byte[] gstInImage;
}
