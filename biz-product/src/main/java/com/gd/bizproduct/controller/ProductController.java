package com.gd.bizproduct.controller;

import com.gd.bizproduct.feign.UserService;
import com.gd.bizproduct.service.ProductService;
import com.gd.common.vo.BaseResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("demo")
    public BaseResponseVO info() {
        return BaseResponseVO.success(productService.getFavorite());
    }

    @RequestMapping(value = "hello-user")
    public BaseResponseVO hello() {
        return BaseResponseVO.success(userService.invokerSayHello("world"));
    }

    @RequestMapping(value = "say-user")
    public BaseResponseVO say() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Andrew");
        map.put("emial", "alffei@163.com");
        return BaseResponseVO.success(userService.invokerSay(map));
    }
}
