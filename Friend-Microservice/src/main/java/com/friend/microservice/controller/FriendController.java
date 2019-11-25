package com.friend.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.friend.microservice.entity.Friend;
import com.friend.microservice.service.FriendService;

@RestController
public class FriendController {
	@Autowired
	private  FriendService  service;
	
	@PostMapping(value="/addFriend", consumes="application/json", produces="text/plain")
	public  String  addFriend(@RequestBody  Friend  friend) {
		boolean flag=service.addFriend(friend);
		if(flag==true) {
			return  "Friend is added successfully";
		}
		else {
			return  "sorry, id already exist";
		}
	}
	
	@GetMapping(value="/friends/{phoneNo}",  produces="application/json")
	public  List<Long>   getFriends(@PathVariable Long phoneNo) {
		return   service.getFriends(phoneNo);
	}

}
