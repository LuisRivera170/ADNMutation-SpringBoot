package com.springboot.mutation.api.service;

import java.util.List;

import com.springboot.mutation.api.entity.Human;

public interface IHumanService {

	public List<Human> findAll();
	
	public Human save(Human newHuman);
	
	public boolean hasMutation(String[] dna);
	
}
