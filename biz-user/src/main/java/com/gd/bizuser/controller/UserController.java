package com.gd.bizuser.controller;

import com.gd.bizuser.controller.vo.LoginParam;
import com.gd.bizuser.service.UserService;
import com.gd.common.exception.CommonServiceException;
import com.gd.common.utils.JwtTokenUtil;
import com.gd.common.vo.BaseResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description : 用户模块表现层
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResponseVO login(@RequestBody LoginParam param) throws CommonServiceException {

        // 数据验证
        param.checkParam();

        // 验证用户名和密码是否正确
        String userId = userService.checkUserLogin(param.getUsername(),param.getPassword());

        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

        String randomKey = jwtTokenUtil.getRandomKey();
        String token = jwtTokenUtil.generateToken(userId, randomKey);

        /*
            createTime
            过期时间
            randomkey - JWT数据签名： AES -> 源数据 + 盐 -> 在token中解析出randomkey -> 数据验签
            userid - 用户身份验证
         */

        // randomKey  token
        Map<String,String> result = new HashMap<>();
        result.put("randomKey",randomKey);
        result.put("token",token);

        return BaseResponseVO.success(result);
    }

}
