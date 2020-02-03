package com.springau.sellerportal.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.*")
public class AppConfig {
	
	private static final String URL = "url";
	private static final String USER = "dbuser";
	private static final String DRIVER = "driver";
	private static final String PASSWORD = "dbpassword";
	
	private static final String MAIL_HOST = "mail.host";
	private static final String MAIL_PORT = "mail.port";
	private static final String MAIL_PROTOCOL =  "mail.protocol";
	private static final String MAIL_SENDER = "mail.sender";
	
	@Autowired
	Environment environment;

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(environment.getProperty(URL));
		driverManagerDataSource.setUsername(environment.getProperty(USER));
		driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
		driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
		return driverManagerDataSource;
	}
	
	
	@Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(environment.getProperty(MAIL_HOST));
        javaMailSender.setProtocol(environment.getProperty(MAIL_PROTOCOL));
        javaMailSender.setPort(Integer.parseInt(environment.getProperty(MAIL_PORT)));
        return javaMailSender;
    }

    @Bean
    public SimpleMailMessage simpleMailMessage() {
       SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
       simpleMailMessage.setFrom(environment.getProperty(MAIL_SENDER));
       return simpleMailMessage;
    }
	
}
	
	
