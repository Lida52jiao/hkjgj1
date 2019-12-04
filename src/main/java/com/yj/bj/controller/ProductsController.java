package com.yj.bj.controller;

import com.yj.bj.entity.Products;
import com.yj.bj.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("Products")
public class ProductsController extends BaseController {

    @Autowired
    private ProductsService productsService;

    @RequestMapping(value = "select")
    public @ResponseBody
    Products select(String type, String appId) {
        Products s = new Products();
        s.setType(type);
        s.setAppId(appId);
        Products products = productsService.findByObject(s);
        return products;
    }
}
