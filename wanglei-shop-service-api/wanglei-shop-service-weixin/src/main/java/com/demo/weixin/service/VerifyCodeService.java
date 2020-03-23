package com.demo.weixin.service;

import com.alibaba.fastjson.JSONObject;
import com.demo.common.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 微信验证码接口
 */
@Api(tags = "微信注册码验证码接口")
public interface VerifyCodeService {
    /**
     * 根据手机号校验用户输入的验证码是否正确
     * @param phone
     * @param code
     * @return
     */
    @ApiOperation(value = "根据手机号码验证码token是否正确")
    @GetMapping("/verifyCode")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "phone", dataType = "String", required = true, value = "用户手机号码"),
            @ApiImplicitParam(paramType = "query", name = "code", dataType = "String", required = true, value = "微信注册码") })
    public BaseResponse<JSONObject> verifyCode(String phone,String code);
}
