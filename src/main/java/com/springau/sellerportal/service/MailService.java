package com.springau.sellerportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.springau.sellerportal.model.Product;
import com.springau.sellerportal.model.Seller;

/**
 * The Class MailService.
 *	Provider for sending
 * @author Rohit Gonsalves
 */
@Service
public class MailService {
	
	/** The java mail sender injected . */
	@Autowired
	private JavaMailSender javaMailSender; 
	
	/** The mail message template Injected. */
	@Autowired
	private SimpleMailMessage mailMessage;
	
	@Autowired
	private SellerService sellerService;
 
	/**
	 * Send the Mail.
	 *
	 * @param toAddress the Receiver Address
	 * @param subject the Mail Subject
	 * @param msgBody the Mail Body
	 */
	public void sendEmail(String toAddress, String subject, String msgBody) {
 
		mailMessage.setTo(toAddress);
		mailMessage.setSubject(subject);
		mailMessage.setText(msgBody);
		javaMailSender.send(mailMessage);
	}
	
	public void sendDeleteWarning(Seller seller, Product product, int quantity, int soldQuantity) {
		
		String subject = "Product Information about " + "| " + seller.getName();
		
		String body = "Dear " + seller.getName() + "," +
						" Your request to delete the product " + product.getName() +
						" has been rejected because the sold quantity (" + soldQuantity + 
						") exceeds half of the remaining quantity (" + quantity + ")";
		
		sendEmail(seller.getEmail(), subject, body);
		
	}
	
	public void sendRejectedEmail(String emailId, String reason) {
		
		Seller seller = sellerService.getSellerByEmail(emailId);
		String body = "Dear " + seller.getName() + "," +
				" Your request to Register on the platform has been rejected. \n Please Sign up again\n Reason: \n" + reason; 
		
		String subject = "Rejection Email|" + seller.getName();
		
		sendEmail(seller.getEmail(), subject, body);
	}

}
