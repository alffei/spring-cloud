package com.gd.bizproduct.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @FeignClient
 * value 为服务提供者的application name
 * 注意：FeignClient暂不支持@PostMapping,@GetMapping等Spring 5的新封装注解
 * 默认会根据此注解接口,生成实现类
 * 如果想自定义实现类，将primary设置为false
 */
@FeignClient(value = "user-service")
//@FeignClient(path = "http://localhost:8201", name="userService")
@RequestMapping(path = "/user")
public interface UserService {

    /**
     * 调用Get Http Method
     * @param message
     * @return
     */
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String invokerSayHello(@RequestParam("message") String message);

    /**
     * 调用Post Http Method
     * @param data
     * @return
     */
    @RequestMapping(value = "/say", method = RequestMethod.POST)
    String invokerSay(@RequestBody Map<String, String> data);
}
