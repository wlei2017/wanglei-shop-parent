package com.demo.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.demo.common.BaseApiService;
import com.demo.common.BaseResponse;
import com.demo.constants.Constants;
import com.demo.member.feign.VerificaCodeServiceFeign;
import com.demo.member.input.UserInpDTO;
import com.demo.member.mapper.UserMapper;
import com.demo.member.mapper.entity.UserDo;
import com.demo.member.service.MemberRegisterService;
import com.demo.utils.MD5Util;
import com.demo.utils.WLBeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRegisterServiceImpl extends BaseApiService<JSONObject> implements MemberRegisterService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VerificaCodeServiceFeign verificaCodeServiceFeign;
    @Override
    public BaseResponse<JSONObject> register(@RequestBody UserInpDTO userInpDTO, String registCode) {
        // 1.验证参数
        String userName = userInpDTO.getUserName();
        if (StringUtils.isEmpty(userName)) {
            return setResultError("用户名称不能为空!");
        }
        String mobile = userInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        String password = userInpDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空!");
        }
        String newPassWord = MD5Util.MD5(password);
        // 将密码采用MD5加密
        userInpDTO.setPassword(newPassWord);
        // 调用微信接口,验证注册码是否正确
        BaseResponse<JSONObject> resultVerificaWeixinCode = verificaCodeServiceFeign.verifyCode(mobile,
                registCode);
        if (!resultVerificaWeixinCode.getRtnCode().equals(Constants.HTTP_RES_CODE_200)) {
            return setResultError(resultVerificaWeixinCode.getMsg());
        }
        // 将dto转为do
        UserDo userDo = WLBeanUtils.dtoToDo(userInpDTO, UserDo.class);
        int registerResult = userMapper.register(userDo);
        return registerResult > 0 ? setResultSuccess("注册成功") : setResultSuccess("注册失败");
    }
}
