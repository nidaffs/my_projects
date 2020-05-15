package com.nidaff.api.dao;

import com.nidaff.entity.entities.History;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHistoryDao extends JpaRepository<History, Long> {

}
