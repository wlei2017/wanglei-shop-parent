package com.demo.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.demo.common.BaseApiService;
import com.demo.common.BaseResponse;
import com.demo.member.input.UserLoginServerInpDTO;
import com.demo.member.mapper.UserMapper;
import com.demo.member.mapper.entity.UserDo;
import com.demo.member.output.UserOutDTO;
import com.demo.member.service.ServerLoginService;
import com.demo.utils.MD5Util;
import com.demo.utils.WLBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerLoginServiceImpl extends BaseApiService<UserOutDTO> implements ServerLoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResponse<UserOutDTO> login(@RequestBody UserLoginServerInpDTO userLoginServerInpDTO) {
        //参数校验
        String mobile = userLoginServerInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)){
            return setResultError("手机号不能为空！");
        }
        String password = userLoginServerInpDTO.getPassword();
        if (StringUtils.isEmpty(password)){
            return setResultError("登录密码不能为空！");
        }

        // 根据手机号 + 密码查询用户表，判断用户用户名或密码是否正确
        //对密码进行加密
        String encPassword = MD5Util.MD5(password);
        UserDo userDo = userMapper.login(mobile, encPassword);
        if (userDo == null){
            return setResultError("用户名或密码错误");
        }
        UserOutDTO userOutDTO = WLBeanUtils.doToDto(userDo, UserOutDTO.class);
        return setResultSuccess(userOutDTO);
    }
}
