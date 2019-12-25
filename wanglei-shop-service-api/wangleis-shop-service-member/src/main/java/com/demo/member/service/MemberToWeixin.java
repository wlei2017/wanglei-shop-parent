package com.demo.member.service;

import com.demo.weixin.pojo.AppEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "会员调用微信服务")
public interface MemberToWeixin {
    @GetMapping("/memberToWeixin")
    @ApiOperation(value = "会员调用服务接口")
    public AppEntity memberToWeixin();
}
