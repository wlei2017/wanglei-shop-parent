package com.demo.member.service;

import com.demo.common.BaseResponse;
import com.demo.member.input.UserLoginServerInpDTO;
import com.demo.member.output.UserOutDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = "服务中心用户登录接口")
public interface ServerLoginService {
    @PostMapping("/login")
    @ApiOperation(value = "用户登录接口")
    public BaseResponse<UserOutDTO> login(@RequestBody UserLoginServerInpDTO userLoginServerInpDTO);
}
