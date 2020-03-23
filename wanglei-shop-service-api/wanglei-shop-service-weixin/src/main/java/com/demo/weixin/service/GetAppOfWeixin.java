package com.demo.weixin.service;

import com.demo.weixin.pojo.AppEntity;
import com.demo.common.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;

public interface GetAppOfWeixin {
    @GetMapping("/getApp")
    public BaseResponse<AppEntity> getApp();
}
