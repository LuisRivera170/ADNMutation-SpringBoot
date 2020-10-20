package com.springboot.mutation.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.mutation.api.entity.Human;
import com.springboot.mutation.api.service.IHumanService;

@RestController
@RequestMapping("/api")
public class HumanController {

	@Autowired
	private IHumanService humanService;
	
	@PostMapping("/mutation")
	public ResponseEntity<Void> checkMutation(@RequestBody Human human) {
		if (human.getDna() == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} 
		String[] dnaArray = human.getDna().toArray(new String[0]);
					
		if (humanService.hasMutation(dnaArray)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}
	
}