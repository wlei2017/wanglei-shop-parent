package com.demo.member.service;


import com.alibaba.fastjson.JSONObject;
import com.demo.common.BaseResponse;
import com.demo.member.input.UserLoginInpDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = "用户登录接口")
public interface MemberLoginService  {
    @PostMapping("/login1")
    @ApiOperation(value = "用户登录接口")
    public BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO);
}
