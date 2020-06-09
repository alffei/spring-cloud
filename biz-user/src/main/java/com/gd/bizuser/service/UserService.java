package com.gd.bizuser.service;


import com.gd.bizuser.dao.entity.User;
import com.gd.common.exception.CommonServiceException;

/**
 * @description : 用户接口
 **/
public interface UserService {

    String checkUserLogin(String username, String password) throws CommonServiceException;

    User getUserInfo(String name);

}
