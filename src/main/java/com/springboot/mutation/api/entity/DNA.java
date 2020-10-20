package com.springboot.mutation.api.entity;

import java.io.Serializable;
import java.util.List;

public class DNA implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<String> dna;

	public List<String> getDna() {
		return dna;
	}

	public void setDna(List<String> dna) {
		this.dna = dna;
	}
	
}
