package com.springboot.mutation.api.service;

import org.springframework.stereotype.Service;

@Service
public class HumanServiceImpl implements IHumanService {

	
	@Override
	public boolean hasMutation(String[] dna) {
		int hasMutation = 0;
        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna[i].length(); j++) {
                // Right sequence
                if ((j + 3) < dna[i].length()) {
                    if (checkSequence(dna[i].charAt(j), dna[i].charAt(j+1), dna[i].charAt(j+2), dna[i].charAt(j+3))) hasMutation++;
                }
                
                // Down sequence
                if ((i+3) < dna.length) {
                    if (checkSequence(dna[i].charAt(j), dna[i+1].charAt(j), dna[i+2].charAt(j), dna[i+3].charAt(j))) hasMutation++;
                }
                
                // Right down sequence
                if ((i+3) < dna.length && (j + 3) < dna[i].length()) {
                    if (checkSequence(dna[i].charAt(j), dna[i+1].charAt(j+1), dna[i+2].charAt(j+2), dna[i+3].charAt(j+3))) hasMutation++;
                }
                
                if (hasMutation > 1) { return true; }
            }
            
        }
        return false;
	}

	// Check if the sequence letters are equal
	public boolean checkSequence(char... sequence) {
        for (int i = 1; i < sequence.length; i++) { 
            if (sequence[0] != sequence[i]) return false;
        }
        return true;
    }
	
}