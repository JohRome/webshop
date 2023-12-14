package com.temp.webshop.webshop.service;

import com.temp.webshop.webshop.entity.Cart;
import com.temp.webshop.webshop.entity.History;
import com.temp.webshop.webshop.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    public List<Cart> findEarlierCarts(Long userId) {
        List<History> foundHistory = historyRepository.findPurchaseHistoryByUserId(userId);
        return foundHistory.stream()
                .flatMap(history -> history.getEarlierCarts().stream())
                .toList();
    }
}
