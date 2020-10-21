package com.springboot.mutation.api.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.mutation.api.entity.Human;
import com.springboot.mutation.api.entity.Stats;

public interface IHumanDao extends JpaRepository<Human, Long> {
	
	@Query("SELECT h.isMutating AS mutation, count(h.isMutating) AS count from Human h GROUP BY h.isMutating")
	public List<Stats> findStats();
	
}
