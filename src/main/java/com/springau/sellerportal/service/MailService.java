package com.springau.sellerportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender; 
	
	@Autowired
	private SimpleMailMessage mailMessage;
 
	public void crunchifyReadyToSendEmail(String toAddress, String subject, String msgBody) {
 
		mailMessage.setTo(toAddress);
		mailMessage.setSubject(subject);
		mailMessage.setText(msgBody);
		javaMailSender.send(mailMessage);
	}

}
