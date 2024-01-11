package com.temp.webshop.webshop.controller;

import com.temp.webshop.webshop.entity.PurchaseHistory;
import com.temp.webshop.webshop.service.PurchaseHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webshop/history")
public class PurchaseHistoryController {

    private final PurchaseHistoryService purchaseHistoryService;


    @GetMapping("/customer")
    @PreAuthorize("hasRole('USER')")
    public List<PurchaseHistory> getCustomerPurchaseHistories(@AuthenticationPrincipal UserDetails userDetails) {
        return purchaseHistoryService.getCustomerHistory(userDetails.getUsername());
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public List<PurchaseHistory> getAllCustomersPurchaseHistories() {
        return purchaseHistoryService.getAllCustomersPurchaseHistories();
    }
}
