package com.springau.sellerportal.model;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Product {
	
	private int productId; 
	private int sellerId;
	private int category;
	private int quantity;
	private int price;
	private String name;
	private String decription;
	private int rating;
	private List<ProductImage> images;
	private List<CategoryAnswer> attributes;
	private Map<String,CategoryAnswer> questionAnswers;
	
	@Override
	public String toString() {
		System.out.println(productId+","+sellerId+","+category+","+quantity+","+price+","+name+","+decription);
//		for(Map.Entry<String, CategoryAnswer> ent:questionAnswers.entrySet()) {
//			System.out.println(ent.getKey()+","+ent.getValue().getCatAnswer());
//		}
//		attributes.forEach((a)->{
//			System.out.println(a.getCatqId()+","+a.getCatAnswer());
//		});
		
		return super.toString();
	}
	
}
