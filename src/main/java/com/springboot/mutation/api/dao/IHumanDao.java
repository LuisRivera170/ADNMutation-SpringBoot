package com.springboot.mutation.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.mutation.api.entity.Human;

public interface IHumanDao extends JpaRepository<Human, Long> {}
