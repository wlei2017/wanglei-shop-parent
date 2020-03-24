package com.demo.member.service.impl;

import com.demo.common.BaseApiService;
import com.demo.common.BaseResponse;
import com.demo.constants.Constants;
import com.demo.member.mapper.UserMapper;
import com.demo.member.pojo.UserEntity;
import com.demo.member.service.MemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberServiceImpl extends BaseApiService<UserEntity> implements MemberService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public BaseResponse<UserEntity> exitsMobile(String mobile) {
        // 1.验证参数
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        UserEntity userEntity = userMapper.existMobile(mobile);
        if (userEntity == null) {
            return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_202, "用户不存在");
        }
        // 注意需要将敏感数据进行脱敏
        userEntity.setPassword(null);
        return setResultSuccess(userEntity);

    }
}
