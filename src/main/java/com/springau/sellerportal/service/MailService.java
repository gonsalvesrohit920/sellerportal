package com.springau.sellerportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
 
	/**
	 * Send the Mail.
	 *
	 * @param toAddress the Receiver Address
	 * @param subject the Mail Subject
	 * @param msgBody the Mail Body
	 */
	public void crunchifyReadyToSendEmail(String toAddress, String subject, String msgBody) {
 
		mailMessage.setTo(toAddress);
		mailMessage.setSubject(subject);
		mailMessage.setText(msgBody);
		javaMailSender.send(mailMessage);
	}

}
