package com.demo.weixin.service.impl;

import com.demo.weixin.pojo.AppEntity;
import com.demo.weixin.service.GetAppOfWeixin;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetAppImpl implements GetAppOfWeixin {
    @Override
    public AppEntity getApp() {
        return new AppEntity("110","dajiahao");
    }
}
