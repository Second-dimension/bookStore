package com.bookStore.client.product.dao;

import com.bookStore.commons.beans.Notice;
import com.bookStore.commons.beans.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: Mac-book
 * Create Data: 2019/5/21
 */
public interface IProductDao {

    List<Product> selectProductByCategory(Map map);

    int selectProductByCategoryCount(@Param("category") String category);

    List<Product> selectProductByName(Map map);

    int selectProductByNameCount(@Param("name") String name);

    Product selectProductById(String id);

    Notice selectRecentNotice();

    List<Product> selectWeekHotProduct();
}
