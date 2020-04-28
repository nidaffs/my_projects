package com.nidaff.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nidaff.entity.entities.History;

@Repository
public interface IHistoryDao extends JpaRepository<History, Long> {
	
}