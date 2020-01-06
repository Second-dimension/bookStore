package com.bookStore.client.cart.dao;

import com.bookStore.commons.beans.Order;
import com.bookStore.commons.beans.Orderitem;

public interface ICartDao {
    void insertOrder(Order order);

    void insertOrderItem(Orderitem item);

    void updateProductNum(Orderitem item);

    void updatePaystate(String order_id);
}
