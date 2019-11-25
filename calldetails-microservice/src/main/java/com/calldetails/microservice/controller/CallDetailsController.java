package com.calldetails.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.calldetails.microservice.dto.CallDetailsDTO;
import com.calldetails.microservice.service.CallDetailsService;

@RestController
public class CallDetailsController {
	@Autowired
	private  CallDetailsService  service;
	
	@GetMapping(value="/{phoneNumber}", produces="application/json")
	public  List<CallDetailsDTO>  getCallDetailsByCalledBy(@PathVariable("phoneNumber") Long  calledby) {
		return  service.getCallDetailsByCalledBy(calledby);
	}
}



