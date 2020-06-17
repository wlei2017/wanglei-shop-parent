package com.demo.pay.service;

import com.alibaba.fastjson.JSONObject;
import com.demo.common.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;

public interface PayContextService {
    @GetMapping("/toHtml")
    public BaseResponse<JSONObject> toHtml(String channelId, String token);
}
