package com.gd.bizproduct.controller;

import com.gd.bizproduct.service.ProductService;
import com.gd.common.vo.BaseResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("demo")
    public BaseResponseVO info() {
        return BaseResponseVO.success(productService.getFavorite());
    }
}
