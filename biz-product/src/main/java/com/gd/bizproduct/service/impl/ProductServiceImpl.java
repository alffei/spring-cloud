package com.gd.bizproduct.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gd.bizproduct.feign.UserService;
import com.gd.bizproduct.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private LoadBalancerClient eurekaClient;

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private UserService userAPI;

    @Override
    public JSONObject getFavorite() {
        ServiceInstance choose = eurekaClient.choose("zuul-gateway");
        String hostname = choose.getHost();
        int port = choose.getPort();
        String uri = "/user-api/user/info/admin_dev";
        String url = "http://"+hostname+":"+port + uri;
        JSONObject baseResponseVO = restTemplate.getForObject(url, JSONObject.class);

//        BaseResponseVO responseVO = userAPI.info("admin_dev");

//        log.info(responseVO.toString());
        // 解析返回值
        JSONObject dataJson = baseResponseVO.getJSONObject("data");
        return dataJson;
    }
}
