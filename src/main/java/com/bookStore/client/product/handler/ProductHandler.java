package com.bookStore.client.product.handler;

import com.bookStore.client.product.service.IProductService;
import com.bookStore.commons.beans.Notice;
import com.bookStore.commons.beans.Product;
import com.bookStore.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Mac-book
 * Create Data: 2019/5/21
 */
@Controller
@RequestMapping("/client/product")
public class ProductHandler {

    @Autowired
    private IProductService productService;

    @RequestMapping("/findProductByCategory.do")
    public String findProductByCategory(@RequestParam(defaultValue = "1") int pageIndex, String category, Model model){
        //System.out.println(category);
        PageModel pageModel =new PageModel();
        pageModel.setPageIndex(pageIndex);

        int count = productService.findProductByCategoryCount(category);
        pageModel.setRecordCount(count);

        List<Product> products = productService.findProductByCategory(category,pageModel);
        model.addAttribute("category",category);
        model.addAttribute("products",products);
        model.addAttribute("pageModel",pageModel);
        /*for (Product p:products){
            System.out.println(p);
        }*/
        return "/client/product_list.jsp";
    }

    @RequestMapping("/findProductByName.do")
    public String findProductByName(@RequestParam(defaultValue = "1") int pageIndex,String name,Model model){
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);

        int count = productService.findProductByNameCount(name);
        pageModel.setRecordCount(count);

        List<Product> products = productService.findProductByName(name,pageModel);
        model.addAttribute("products",products);
        model.addAttribute("name",name);
        model.addAttribute("pageModel",pageModel);
        return "/client/product_search_list.jsp";
    }

    @RequestMapping("/findProductById.do")
    public String findProductById(String id,Model model){
        Product product = productService.findProductById(id);
        model.addAttribute("p", product);
        return "/client/info.jsp";
    }

    @RequestMapping("/showIndex.do")
    public String showIndex(Model model){
        Notice notice = productService.findRecentNotice();
        model.addAttribute("n",notice);
        List<Product> products = productService.findWeekHotProduct();
        model.addAttribute("products",products);
        return "/client/index.jsp";
    }

}
