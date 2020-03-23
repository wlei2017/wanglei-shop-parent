package com.demo.weixin.service.impl;

import com.demo.weixin.pojo.AppEntity;
import com.demo.weixin.service.GetAppOfWeixin;
import com.demo.common.BaseApiService;
import com.demo.common.BaseResponse;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetAppImpl extends BaseApiService<AppEntity> implements GetAppOfWeixin {
    @Override
    public BaseResponse<AppEntity> getApp() {
//        return new AppEntity("110","dajiahao");
        return setResultSuccess(new AppEntity("110","dajiahao"));
    }

}
