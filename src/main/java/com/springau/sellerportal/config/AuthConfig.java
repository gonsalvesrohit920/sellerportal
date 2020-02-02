/*
 * package com.springau.sellerportal.config;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.web.authentication.www.
 * BasicAuthenticationEntryPoint;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class AuthConfig extends
 * WebSecurityConfigurerAdapter{
 * 
 * @Autowired private BasicAuthenticationEntryPoint entryPoint;
 * 
 * @Override protected void configure(AuthenticationManagerBuilder
 * authenticationManagerBuilder) throws Exception {
 * 
 * authenticationManagerBuilder .inMemoryAuthentication() .withUser("User")
 * .password("password") .roles("Admin");
 * 
 * }
 * 
 * 
 * @Override protected void configure(HttpSecurity httpSecurity) throws
 * Exception { httpSecurity.authorizeRequests() .antMatchers("/seller/login")
 * .permitAll() .anyRequest() .authenticated() .and() .httpBasic()
 * .authenticationEntryPoint(entryPoint);
 * 
 * httpSecurity.addFilterAfter(new CustomF, afterFilter) } }
 */