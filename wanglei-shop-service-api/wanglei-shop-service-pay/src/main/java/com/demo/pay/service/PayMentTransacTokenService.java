package com.demo.pay.service;

import com.alibaba.fastjson.JSONObject;
import com.demo.common.BaseResponse;
import com.demo.pay.input.PayCratePayTokenDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

public interface PayMentTransacTokenService {
    /**
     * 创建支付令牌
     *
     * @return
     */
    @GetMapping("/cratePayToken")
    public BaseResponse<JSONObject> cratePayToken(@Validated PayCratePayTokenDto payCratePayTokenDto);
}
