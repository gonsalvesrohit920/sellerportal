package com.springau.sellerportal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springau.sellerportal.model.Order;
import com.springau.sellerportal.model.Product;
import com.springau.sellerportal.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping(path = "/PlaceOrder")
	public int placeOrder(@RequestBody Order order) {
		return orderService.placeOrder(order);
		
	}
	
	@GetMapping(path="/GetOrder/{sellerId}")
	public Map<Integer,Product> getOrdersOfSeller(@PathVariable("sellerId") int sellerId) {
		return orderService.getOrdersOfSeller(sellerId);
	}
	
	@PutMapping(path="SubmitRating/{o_id}")
	public int submitRating(@PathVariable("o_id") int orderId, @RequestBody int rating) {
		return orderService.submitRating(orderId, rating);
	}

}
