package com.demo.member.service.impl;

import com.demo.common.BaseApiService;
import com.demo.common.BaseResponse;
import com.demo.constants.Constants;
import com.demo.member.mapper.UserMapper;
import com.demo.member.mapper.entity.UserDo;
import com.demo.member.output.UserOutDTO;
import com.demo.member.service.MemberService;
import com.demo.token.GenerateToken;
import com.demo.type.TypeCastHelper;
import com.demo.utils.WLBeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberServiceImpl extends BaseApiService<UserOutDTO> implements MemberService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GenerateToken generateToken;
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

    @Override
    public BaseResponse<UserOutDTO> getInfo(String token) {
        // 1.验证token参数
        if (StringUtils.isEmpty(token)) {
            return setResultError("token不能为空!");
        }
        // 2.使用token查询redis 中的userId
        String redisUserId = generateToken.getToken(token);
        if (StringUtils.isEmpty(redisUserId)) {
            return setResultError("token已经失效或者token错误!");
        }
        // 3.使用userID查询 数据库用户信息
        Long userId = TypeCastHelper.toLong(redisUserId);
        UserDo userDo = userMapper.findByUserId(userId);
        if (userDo == null) {
            return setResultError("用户不存在!");
        }
        // 下节课将 转换代码放入在BaseApiService
        return setResultSuccess(WLBeanUtils.doToDto(userDo, UserOutDTO.class));
    }
}
