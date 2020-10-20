package com.springboot.mutation.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.mutation.api.entity.DNA;

@RestController
@RequestMapping("/api")
public class MutationController {

	@PostMapping("/mutation")
	public ResponseEntity<?> checkMutation(@RequestBody DNA dnaRequest) {
		
		return null;
	}
	
}
