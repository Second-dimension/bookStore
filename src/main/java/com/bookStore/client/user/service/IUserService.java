package com.bookStore.client.user.service;

import com.bookStore.commons.beans.Order;
import com.bookStore.commons.beans.Orderitem;
import com.bookStore.commons.beans.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {
    int addUser(User user, HttpServletRequest request);

    int activeUser(String activeCode);

    User findEmail(String email);

    User findUsername(String username);

    User findUserByLogin(User user);

    int modifyUser(User user);

    List<Order> findOrderByUser(Integer id);

    List<Orderitem> findOrderitemById(String id);

    void removeOrderById(String id, String flag);
}
