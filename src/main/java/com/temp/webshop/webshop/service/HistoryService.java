package com.temp.webshop.webshop.service;

import com.temp.webshop.authentication.entity.Customer;
import com.temp.webshop.authentication.repository.UserRepository;
import com.temp.webshop.webshop.entity.History;
import com.temp.webshop.webshop.entity.ShoppingCart;
import com.temp.webshop.webshop.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    public List<ShoppingCart> findEarlierShoppingCarts(Long userId) {
        List<History> foundHistory = historyRepository.findHistoryByUserId(userId);
        return foundHistory.stream()
                .flatMap(history -> history.getEarlierShoppingCarts().stream())
                .toList();
    }
}
