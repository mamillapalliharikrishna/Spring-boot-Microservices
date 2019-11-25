package com.customer.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.customer.microservice.dto.CustomerDTO;
import com.customer.microservice.dto.Login;
import com.customer.microservice.dto.PlanDetailsDTO;
import com.customer.microservice.entity.Customer;
import com.customer.microservice.service.CustomerService;

@RestController
public class CustomerController {
	
	String    FRIEND_URL="http://localhost:4003/FriendMicroservice/friends/{phoneNo}";
	String   PLAN_URL="http://localhost:4002/PlanDetailsMicroservice/{planId}";
	@Autowired
	CustomerService   service;
	@Autowired
	RestTemplate   restTemplate;
	
	@PostMapping(value="/register",  consumes="application/json",  produces="text/plain")
	public    String    registerCustomer(@RequestBody   Customer  customer) {
		return    service.saveCustomer(customer);
	}
	
	@PostMapping(value="/login",  consumes="application/json")
	public    boolean    verifyLogin(@RequestBody  Login   login) {
		return    service.checkLogin(login);
	}
	
	@GetMapping(value="/viewProfile/{phoneNo}",  produces="application/json")
	public      CustomerDTO    viewProfile(@PathVariable  Long   phoneNo) {
		CustomerDTO   dto = service.getCustomerByPhoneNo(phoneNo);
		
		//calling   Friend-Microservice
		//List<Long>  friends=restTemplate.getForObject(FRIEND_URL, List<Long>.class, phoneNo);
		ResponseEntity<List<Long>>    friends=restTemplate.exchange(FRIEND_URL, HttpMethod.GET,null,new ParameterizedTypeReference<List<Long>>() { }, phoneNo);
		List<Long>  friendsList=friends.getBody();
		dto.setFriends(friendsList);
		
		//calling   Plan-Microservice
		Long   planId=dto.getPlanId();
		PlanDetailsDTO   planDto =restTemplate.getForObject(PLAN_URL, PlanDetailsDTO.class, planId);
		dto.setCurrentPlan(planDto);
		
		return  dto;
	}
}
