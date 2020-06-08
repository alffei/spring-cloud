package com.gd.common.vo;

import com.gd.common.exception.CommonServiceException;

/**
 * @description : 公共的请求对象
 **/
public abstract class BaserRequestVO {

    /**
    * @Description: 公共的参数验证方法
    * @Param: []
    * @return: void
    */
    public abstract void checkParam() throws CommonServiceException;

}
