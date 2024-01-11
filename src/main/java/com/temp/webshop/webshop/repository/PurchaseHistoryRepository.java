package com.temp.webshop.webshop.repository;

import com.temp.webshop.webshop.entity.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {

    List<PurchaseHistory> findPurchaseHistoriesByCustomerUsername(String username);

}
