package com.demo.member.service;

import com.demo.common.BaseResponse;
import com.demo.member.output.UserOutDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "会员服务接口")
public interface MemberService {
    @ApiOperation(value = "查询手机号是否已经注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "mobile",dataType = "String",
            required = true,value = "用户手机号码")
    })
    @PostMapping("/existMobile")
    public BaseResponse<UserOutDTO> exitsMobile(@RequestParam("mobile") String mobile);
}
