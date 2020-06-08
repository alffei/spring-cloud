package com.gd.bizuser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gd.bizuser.dao.entity.User;
import com.gd.bizuser.dao.mapper.UserMapper;
import com.gd.bizuser.service.UserService;
import com.gd.common.exception.CommonServiceException;
import com.gd.common.utils.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description : 用户模块业务实现
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public String checkUserLogin(String username, String password) throws CommonServiceException {
        // 根据用户名获取用户信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",username);

        // 避免数据出现问题
        List<User> list = userMapper.selectList(queryWrapper);
        User user = null;
        if(list !=null && list.size()>0){
            user = list.stream().findFirst().get();
        }else{
            throw new CommonServiceException(404, "用户名输入有误");
        }

        // 验证密码是否正确【密码要做MD5加密，才能验证是否匹配】
        String encrypt = MD5Util.encrypt(password);

        if(!encrypt.equals(user.getUserPwd())){
            throw new CommonServiceException(500,"用户密码输入有误");
        }else{
            return user.getUuid()+"";
        }
    }

}
