package com.bookStore.client.user.service;

import com.bookStore.client.user.dao.IUserDao;
import com.bookStore.commons.beans.Order;
import com.bookStore.commons.beans.Orderitem;
import com.bookStore.commons.beans.User;
import com.bookStore.utils.MailUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public int addUser(User user, HttpServletRequest request) {
        //System.out.println(user+"service");
        int row = 0;
        String emailMsg = "感谢注册网上书城，请点击<a href='http://localhost:8080"
                +request.getContextPath()+"/client/user/activeUser.do?activeCode="
                +user.getActiveCode()+"'>激活</a>后使用！";
        try {
            MailUtils.sendMail(user.getEmail(),emailMsg);
            row = userDao.insertUser(user);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int activeUser(String activeCode) {
        return userDao.activeUser(activeCode);
    }

    @Override
    public User findEmail(String email) {
        return userDao.selectEmail(email);
    }

    @Override
    public User findUsername(String username) {

        return userDao.selectUsername(username);
    }

    @Override
    public User findUserByLogin(User user) {
        return userDao.selectUserByLogin(user);
    }

    @Override
    public int modifyUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public List<Order> findOrderByUser(Integer id) {

        return userDao.selectOrderByUser(id);
    }

    @Override
    public List<Orderitem> findOrderitemById(String id) {
        return userDao.selectOrderitemById(id);
    }

    @Override
    public void removeOrderById(String id, String flag) {
        //还没有付款的用户订单删除
        if (flag == null){
            List<Orderitem> items = userDao.selectOrderitemById(id);
            for (Orderitem item:items){
                userDao.updateProductnum(item);
            }
        }
        userDao.deleteOrderById(id);
        userDao.deleteOrderItemById(id);
    }


}
