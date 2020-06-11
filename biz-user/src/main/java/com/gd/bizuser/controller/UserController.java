package com.gd.bizuser.controller;

import com.gd.bizuser.controller.vo.LoginParam;
import com.gd.bizuser.service.UserService;
import com.gd.common.exception.CommonServiceException;
import com.gd.common.utils.JwtTokenUtil;
import com.gd.common.vo.BaseResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @description : 用户模块表现层
 **/
@RestController
@Slf4j
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
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
            MD5()

         */

        // randomKey  token
        Map<String,String> result = new HashMap<>();
        result.put("randomKey",randomKey);
        result.put("token",token);

        return BaseResponseVO.success(result);
    }

//    @GetMapping("/info/{name}")
    @RequestMapping(value = "/info/{name}", method = RequestMethod.GET)
    public BaseResponseVO info(@PathVariable("name") String name ) {
        return BaseResponseVO.success(userService.getUserInfo(name));
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String providerSayHello(@RequestParam("message") String message){
        log.error("hello,{}", message);
        return "hello," + message;
    }

    @RequestMapping(value = "/say", method = RequestMethod.POST)
    public String providerSay(@RequestBody Map<String, String> data){
        StringBuilder stringBuilder = new StringBuilder("");
        Iterator<String> iterator = data.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            stringBuilder.append(key).append(":").append(data.get(key)).append("; ");
        }
        return stringBuilder.toString();
    }

}
