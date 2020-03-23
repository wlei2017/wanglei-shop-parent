package com.demo.member.service.impl;

import com.demo.member.feign.WeixinFeign;
import com.demo.member.service.MemberToWeixin;
import com.demo.weixin.pojo.AppEntity;
import com.demo.common.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberToWeixinImpl implements MemberToWeixin {
    @Autowired
    private WeixinFeign weixinFeign;
    @Override
    public BaseResponse<AppEntity> memberToWeixin() {
        return weixinFeign.getApp();
    }
}
