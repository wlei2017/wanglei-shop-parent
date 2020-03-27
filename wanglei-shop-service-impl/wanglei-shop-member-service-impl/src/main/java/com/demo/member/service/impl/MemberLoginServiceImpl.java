package com.demo.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.demo.common.BaseApiService;
import com.demo.common.BaseResponse;
import com.demo.constants.Constants;
import com.demo.member.input.UserLoginInpDTO;
import com.demo.member.mapper.UserMapper;
import com.demo.member.mapper.UserTokenMapper;
import com.demo.member.mapper.entity.UserDo;
import com.demo.member.mapper.entity.UserTokenDo;
import com.demo.member.service.MemberLoginService;
import com.demo.token.GenerateToken;
import com.demo.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MemberLoginServiceImpl extends BaseApiService<JSONObject> implements MemberLoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Autowired
    private GenerateToken generateToken;

    @Override
    public BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO) {
        //进行参数校验
        String loginType = userLoginInpDTO.getLoginType();
        if (StringUtils.isEmpty(loginType)){
            return setResultError("登录类型不能为空！");
        }
        if ( !(Constants.LOGIN_TYPE_PC.equals(loginType) || Constants.LOGIN_TYPE_ANDROID.
                equals(loginType) || Constants.LOGIN_TYPE_IOS.equals(loginType))){
            return setResultError("登录类型错误");
        }
        String mobile = userLoginInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)){
            return setResultError("手机号不能为空！");
        }
        String password = userLoginInpDTO.getPassword();
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
        //根据 userId、登录类型和是否可用查询用户之前是否登录过
        Long userId = userDo.getUserId();
        UserTokenDo userTokenDo = userTokenMapper.selectByUserIdAndLoginType(userId, loginType);

        //插入新的登录信息到唯一登录表
        //首先需要生成新的token
        String prifix = Constants.MEMBER_TOKEN_PRE + loginType + ":";
        String newToken = generateToken.createToken(prifix, String.valueOf(userId));
        UserTokenDo userTokenDo1 = new UserTokenDo();
        userTokenDo1.setDeviceInfor(userLoginInpDTO.getDeviceInfor());
        userTokenDo1.setLoginType(userLoginInpDTO.getLoginType());
        userTokenDo1.setToken(newToken);
        userTokenDo1.setUserId(userId);
        userTokenMapper.insertUserToken(userTokenDo1);

        //将老的token从redis删除
        if (userTokenDo != null){
            //说明已经登录过，需要更新为不可用
            String token = userTokenDo.getToken();
            userTokenMapper.updateTokenAvailability(token);
            generateToken.removeToken(token);
        }
        JSONObject data = new JSONObject();
        data.put("token",newToken);
        return setResultSuccess(data);
    }
}
