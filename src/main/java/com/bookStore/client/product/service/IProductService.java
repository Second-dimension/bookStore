package com.bookStore.client.product.service;

import com.bookStore.commons.beans.Notice;
import com.bookStore.commons.beans.Product;
import com.bookStore.utils.PageModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Mac-book
 * Create Data: 2019/5/21
 */
public interface IProductService {

    List<Product> findProductByCategory(String category, PageModel pageModel);

    int findProductByCategoryCount(String category);

    List<Product> findProductByName(String name, PageModel pageModel);

    int findProductByNameCount(String name);

    Product findProductById(String id);

    Notice findRecentNotice();

    List<Product> findWeekHotProduct();
}
