package com.demo.member.service.impl;

import com.demo.common.BaseApiService;
import com.demo.common.BaseResponse;
import com.demo.constants.Constants;
import com.demo.member.mapper.UserMapper;
import com.demo.member.mapper.entity.UserDo;
import com.demo.member.output.UserOutDTO;
import com.demo.member.service.MemberService;
import com.demo.utils.WLBeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberServiceImpl extends BaseApiService<UserOutDTO> implements MemberService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public BaseResponse<UserOutDTO> exitsMobile(String mobile) {
        // 1.验证参数
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        UserDo userDo = userMapper.existMobile(mobile);
        if (userDo == null) {
            return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_202, "用户不存在");
        }
        // 将do转为dto
        UserOutDTO userOutDTO = WLBeanUtils.doToDto(userDo, UserOutDTO.class);

        return setResultSuccess(userOutDTO);

    }
}
