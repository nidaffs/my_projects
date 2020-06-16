package com.nidaff.api.dao;

import com.nidaff.entity.entities.History;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHistoryDao extends JpaRepository<History, Long> {

    public History findHistoryByUserIdAndBookIdAndIsTaken(Long userId, Long bookId, boolean isTaken);

    public List<History> findHistoryByUserId(Long id);
    
    public List<History> findHistoryByIsTaken(boolean isTaken);
    
}
