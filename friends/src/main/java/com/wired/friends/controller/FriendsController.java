package com.wired.friends.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wired.friends.model.Friend;
import com.wired.friends.service.FriendService;

@RestController
public class FriendsController {

	@Autowired
	FriendService friendService;
	
	@PostMapping("/friend")
	Friend create (@RequestBody Friend friend) {
		return friendService.save(friend);
	}
	
	@GetMapping("/friend")
	Iterable<Friend> read(){
		return friendService.findAll();
	}
	
	@GetMapping("/friend/{id}")
	Optional<Friend> findById(@PathVariable Integer id) {
		return friendService.findById(id);
	}
	
	@GetMapping("/friend/search")
	Iterable<Friend> findByQuery(@RequestParam(value="first",required=false) String firstName, @RequestParam(value="last",required=false) String lastName){
		
		if(firstName != null && lastName!= null)
			return friendService.findByFirstNameAndLastName(firstName, lastName);
		if(lastName == null)
			return friendService.findByFirstName(firstName);
		if(firstName == null)
			return friendService.findByLastName(lastName);
		else
			return friendService.findAll();
	}
	
	@PutMapping("/friend")
	Friend update (@RequestBody Friend friend) {
		return friendService.save(friend);
	}
	
	@DeleteMapping("/friend/{id}")
	void delete(@PathVariable Integer id) {
		friendService.deleteById(id);
	}
	
}
