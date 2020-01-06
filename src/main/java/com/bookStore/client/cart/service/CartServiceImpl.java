package com.bookStore.client.cart.service;

import com.bookStore.client.cart.dao.ICartDao;
import com.bookStore.commons.beans.Order;
import com.bookStore.commons.beans.Orderitem;
import com.bookStore.commons.beans.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: Mac-book
 * Create Data: 2019/5/29
 */
@Service
public class CartServiceImpl implements ICartService {

    @Resource
    private ICartDao cartDao;

    @Override
    public void createOrder(Order order, Map<Product, Integer> cart) {
        //循环插入订单项，修改商品数量
        for (Product p:cart.keySet()){
            Orderitem item = new Orderitem();
            item.setOrder(order);
            item.setProduct(p);
            item.setBuynum(cart.get(p));
            cartDao.updateProductNum(item);
            cartDao.insertOrderItem(item);
        }
        //创建订单，插入订单表的数据
        cartDao.insertOrder(order);
    }

    @Override
    public void paysuccess(String order_id) {
        cartDao.updatePaystate(order_id);
    }


}
