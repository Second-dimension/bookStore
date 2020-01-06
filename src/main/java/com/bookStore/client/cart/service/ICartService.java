package com.bookStore.client.cart.service;

import com.bookStore.commons.beans.Order;
import com.bookStore.commons.beans.Product;

import java.util.Map;

/**
 * company: www.abc.com
 * Author: Mac-book
 * Create Data: 2019/5/29
 */
public interface ICartService {

    void createOrder(Order order, Map<Product, Integer> cart);

    void paysuccess(String order_id);
}
