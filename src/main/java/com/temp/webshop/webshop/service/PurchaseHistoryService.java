package com.temp.webshop.webshop.service;

import com.temp.webshop.auth.entity.Customer;
import com.temp.webshop.auth.repository.CustomerRepository;
import com.temp.webshop.webshop.entity.PurchaseHistory;
import com.temp.webshop.webshop.repository.CartRepository;
import com.temp.webshop.webshop.repository.PurchaseHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
