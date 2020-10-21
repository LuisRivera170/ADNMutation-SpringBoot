package com.springboot.mutation.api.service;

import java.util.List;
import java.util.Map;

import com.springboot.mutation.api.entity.Human;

public interface IHumanService {

	public List<Human> findAll();
	
	public Human save(Human newHuman);
	
	public boolean hasMutation(String[] dna);
	
	public Map<String, Object> findStats();
	
}
