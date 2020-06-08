package com.gd.common.vo;

import com.gd.common.exception.CommonServiceException;
import lombok.Data;

/**
 * @description : 分页请求类
 **/
@Data
public class BasePageVO extends BaserRequestVO{

    private Integer nowPage = 1;
    private Integer pageSize = 10;

    @Override
    public void checkParam() throws CommonServiceException {

        // TODO nowpage和pageSize不能为空 balaba

    }
}
