package com.springau.pojoTest;

import org.junit.Test;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.springau.sellerportal.model.Category;
import com.springau.sellerportal.model.CategoryAnswer;
import com.springau.sellerportal.model.CategoryQuestion;
import com.springau.sellerportal.model.Contact;
import com.springau.sellerportal.model.Documents;
import com.springau.sellerportal.model.LoginData;
import com.springau.sellerportal.model.OrderData;
import com.springau.sellerportal.model.Product;
import com.springau.sellerportal.model.ProductImage;
import com.springau.sellerportal.model.QuestionAnswer;
import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.utility.PojoTestUtils;

public class PojoTest {

	
	@Test
    public void testAccesors_shouldAccessProperField() {
 
        PojoTestUtils.validateAccessors(Seller.class);
        PojoTestUtils.validateAccessors(Category.class);
        PojoTestUtils.validateAccessors(CategoryAnswer.class);
        PojoTestUtils.validateAccessors(CategoryQuestion.class);
        PojoTestUtils.validateAccessors(Contact.class);
        PojoTestUtils.validateAccessors(Documents.class);
        PojoTestUtils.validateAccessors(LoginData.class);
        PojoTestUtils.validateAccessors(Order.class);
        PojoTestUtils.validateAccessors(OrderData.class);
        PojoTestUtils.validateAccessors(Product.class);
        PojoTestUtils.validateAccessors(ProductImage.class);
        PojoTestUtils.validateAccessors(QuestionAnswer.class);
        
        		
    }
    
}
