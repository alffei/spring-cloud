package com.gd.bizproduct.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gd.bizproduct.dao.entity.Product;
import com.gd.bizproduct.service.ProductService;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private LoadBalancerClient eurekaClient;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JSONObject getFavorite() {
        ServiceInstance choose = eurekaClient.choose("user-service");
        String hostname = choose.getHost();
        int port = choose.getPort();
        String uri = "/user/info/admin_dev";
        String url = "http://"+hostname+":"+port + uri;
        JSONObject baseResponseVO = restTemplate.getForObject(url, JSONObject.class);

        // 解析返回值
        JSONObject dataJson = baseResponseVO.getJSONObject("data");
        return dataJson;
    }
}
