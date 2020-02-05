package com.springau.daoTest;


import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.springau.sellerportal.dao.SellerDAO;
import com.springau.sellerportal.model.Contact;
import com.springau.sellerportal.model.Documents;
import com.springau.sellerportal.model.LoginData;
import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.service.SellerService;
import com.springau.sellerportal.utility.PasswordHash;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;
import static org.mockito.BDDMockito.*;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class ProductDAOTest {
	
	

}
