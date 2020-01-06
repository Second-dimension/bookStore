package com.bookStore.client.product.service;

import com.bookStore.client.product.dao.IProductDao;
import com.bookStore.commons.beans.Notice;
import com.bookStore.commons.beans.Product;
import com.bookStore.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: Mac-book
 * Create Data: 2019/5/21
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Resource
    private IProductDao productDao;

    @Override
    public List<Product> findProductByCategory(String category, PageModel pageModel) {
        Map map = new HashMap();
        map.put("category", category);
        map.put("pageModel", pageModel);
        return productDao.selectProductByCategory(map);
    }

    @Override
    public int findProductByCategoryCount(String category) {
        return productDao.selectProductByCategoryCount(category);
    }

    @Override
    public List<Product> findProductByName(String name, PageModel pageModel) {
        Map map = new HashMap();
        map.put("name", name);
        map.put("pageModel", pageModel);
        return productDao.selectProductByName(map);
    }

    @Override
    public int findProductByNameCount(String name) {
        return productDao.selectProductByNameCount(name);
    }

    @Override
    public Product findProductById(String id) {
        return productDao.selectProductById(id);
    }

    @Override
    public Notice findRecentNotice() {
        return productDao.selectRecentNotice();
    }

    @Override
    public List<Product> findWeekHotProduct() {
        return productDao.selectWeekHotProduct();
    }

}