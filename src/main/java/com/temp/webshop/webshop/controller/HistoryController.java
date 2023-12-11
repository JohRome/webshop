package com.temp.webshop.webshop.controller;

import com.temp.webshop.authentication.entity.Customer;
import com.temp.webshop.webshop.entity.ShoppingCart;
import com.temp.webshop.webshop.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchasehistory")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ShoppingCart>> findAllPurchases(
            @PathVariable Long userId
    ) {
       return ResponseEntity.ok(historyService.findEarlierShoppingCarts(userId));
    }
}
