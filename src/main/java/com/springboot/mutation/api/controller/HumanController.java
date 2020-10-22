package com.springboot.mutation.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.mutation.api.entity.Human;
import com.springboot.mutation.api.service.IHumanService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping()
public class HumanController {

	@Autowired
	private IHumanService humanService;
	
	@GetMapping("/humans")
	public List<Human> index() {
		return humanService.findAll();
	}
	
	@PostMapping("/humans")
	public ResponseEntity<?> create(@Valid @RequestBody Human human, BindingResult result) {
		Map<String, Object> response = new HashMap<String, Object>();
		
		if (result.hasErrors()) {
			response.put("errores", result.getFieldErrors());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			Human newHuman = humanService.save(human);
			response.put("human", newHuman);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("messageError", "Error al ingresar el registro humano en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
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
	
	@GetMapping("/stats")
	public Map<String, Object> findStats() {
		return humanService.findStats();
	}
	
}