package com.temp.webshop.webshop.dto;

import com.temp.webshop.webshop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {

    private int quantity;
    private Product product;
}
