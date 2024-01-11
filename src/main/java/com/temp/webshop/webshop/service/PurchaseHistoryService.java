package com.temp.webshop.webshop.service;

import com.temp.webshop.auth.entity.Customer;
import com.temp.webshop.auth.repository.CustomerRepository;
import com.temp.webshop.webshop.entity.PurchaseHistory;
import com.temp.webshop.webshop.repository.CartRepository;
import com.temp.webshop.webshop.repository.PurchaseHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseHistoryService {


    private final PurchaseHistoryRepository purchaseHistoryRepository;
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public void saveReceipt(String username, String receipt, double cost) {
        Customer customer = findCustomer(username);

        PurchaseHistory history = new PurchaseHistory(0L, receipt, cost, customer);

        purchaseHistoryRepository.save(history);
    }

    private Customer findCustomer(String username) {
        // Find customer by username
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the customer is the correct one
        if (!customer.getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized operation");
        } else {
            return customer;
        }
    }

    public List<PurchaseHistory> getCustomerHistory(String username) {
        return purchaseHistoryRepository.findPurchaseHistoriesByCustomerUsername(username);
    }

    public List<PurchaseHistory> getAllCustomersPurchaseHistories() {
        checkAdminRole();
        return purchaseHistoryRepository.findAll();
    }

    private void checkAdminRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(r -> r.equals("ROLE_ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
    }
}
