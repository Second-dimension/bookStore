package com.bookStore.client.user.dao;

import com.bookStore.commons.beans.Order;
import com.bookStore.commons.beans.Orderitem;
import com.bookStore.commons.beans.User;

import java.util.List;

public interface IUserDao {
    int insertUser(User user);

    int activeUser(String activeCode);

    User selectEmail(String email);

    User selectUsername(String username);

    User selectUserByLogin(User user);

    int updateUser(User user);

    List<Order> selectOrderByUser(Integer id);

    List<Orderitem> selectOrderitemById(String id);

    void updateProductnum(Orderitem item);

    void deleteOrderById(String id);

    void deleteOrderItemById(String id);
}
