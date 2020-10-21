package com.springboot.mutation.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springboot.mutation.api.dao.IHumanDao;
import com.springboot.mutation.api.entity.Human;
import com.springboot.mutation.api.entity.Stats;

@Service
public class HumanServiceImpl implements IHumanService {

	@Autowired
	private IHumanDao humanDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Human> findAll() {
		return humanDao.findAll();
	}

	@Override
	@Transactional
	public Human save(Human newHuman) {
		return humanDao.save(newHuman);
	}
	
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

	@Override
	public Map<String, Object> findStats() {
		Map<String, Object> statsMap = new HashMap<String, Object>();
		List<Stats> stats = humanDao.findStats();
		int mutations = 0, noMutations = 0;
		for (Stats stat: stats) {
			if (stat.getMutation()) {
				mutations = stat.getCount();
				statsMap.put("count_mutations", mutations);
			} else {
				noMutations = stat.getCount();
				statsMap.put("count_no_mutations", noMutations);
			}
		}
		statsMap.put("ratio", Float.valueOf(mutations)/Float.valueOf(noMutations));
		return statsMap;
	}
	
}
