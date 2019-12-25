package com.demo.weixin.service;

import com.demo.weixin.pojo.AppEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface GetAppOfWeixin {
    @GetMapping("/getApp")
    public AppEntity getApp();
}
