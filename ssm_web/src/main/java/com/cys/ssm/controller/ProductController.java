package com.cys.ssm.controller;

import com.cys.ssm.domain.Product;
import com.cys.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {

        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll();
        System.out.println(ps);
        mv.addObject("productList", ps);
        mv.setViewName("/product-list");
        return mv;
    }
}
