package com.demo.weixin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.demo.common.BaseApiService;
import com.demo.common.BaseResponse;
import com.demo.constants.Constants;
import com.demo.utils.RedisUtil;
import com.demo.weixin.service.VerifyCodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyCodeImpl extends BaseApiService<JSONObject> implements VerifyCodeService {
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public BaseResponse<JSONObject> verifyCode(String phone, String code) {
        if (StringUtils.isEmpty(phone)){
            return setResultError("手机号码不能为空");
        }
        if (StringUtils.isEmpty(code)){
            return setResultError("验证码不能为空");
        }
        //根据手机号从redis中获取code
        String wxCode = redisUtil.getString(Constants.WEIXINCODE_KEY + phone);
        //判断redis中的code是否过期
        if (StringUtils.isEmpty(wxCode)){
            return setResultError("验证码已经过期，请重新输入手机号申请验证码");
        }
        //判断验证码是否正确
        if (!code.equals(wxCode)){
            return setResultError("验证码不正确，请重新输入");
        }
        return setResultSuccess("验证码正确");
    }
}
