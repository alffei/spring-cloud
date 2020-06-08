package com.gd.bizuser.controller.vo;

import com.gd.common.exception.CommonServiceException;
import com.gd.common.utils.ToolUtils;
import com.gd.common.vo.BaserRequestVO;
import lombok.Data;

/**
 * @description : 登陆请求对象
 **/
@Data
public class LoginParam extends BaserRequestVO {

    private String username;
    private String password;

    @Override
    public void checkParam()  throws CommonServiceException {
        // TODO 验证数据合法性
        if(ToolUtils.strIsNull(username) || ToolUtils.strIsNull(password)){
            throw new CommonServiceException(404,"username 或 password不能为空");
        }

    }

}
