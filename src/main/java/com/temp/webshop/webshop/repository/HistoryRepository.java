package com.temp.webshop.webshop.repository;

import com.temp.webshop.webshop.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Integer> {
    public List<History> findPurchaseHistoryByUserId(Long id);
}
