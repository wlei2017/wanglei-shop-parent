package com.demo.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.demo.common.BaseApiService;
import com.demo.common.BaseResponse;
import com.demo.pay.mapper.PaymentChannelMapper;
import com.demo.pay.mapper.entity.PaymentChannelEntity;
import com.demo.pay.out.PayMentTransacDTO;
import com.demo.pay.service.PayContextService;
import com.demo.pay.service.PayMentTransacInfoService;
import com.demo.pay.service.factory.StrategyFactory;
import com.demo.pay.service.strategy.PayStrategy;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayContextServiceImpl extends BaseApiService<JSONObject> implements PayContextService {
    @Autowired
    private PaymentChannelMapper channelMapper;
    @Autowired
    private PayMentTransacInfoService payMentTransacInfoService;

    @Override
    public BaseResponse<JSONObject> toHtml(String channelId, String token) {
        //1.根据channelId查询渠道表
        PaymentChannelEntity paymentChannelEntity = channelMapper.selectByChannelId(channelId);
        if (paymentChannelEntity == null){
            return setResultError("渠道信息有误！");
        }
        //2.根据token查询支付信息
        BaseResponse<PayMentTransacDTO> payMentTransacDTOBaseResponse = payMentTransacInfoService.tokenByPayMentTransac(token);
        if (payMentTransacDTOBaseResponse.getData() == null){
            return setResultError("没有查到用户信息");
        }

        //3.根据渠道表中的classAddress自动找到具体的支付方式
        //3.1 拿到对应的全路径类名
        String classAddress = paymentChannelEntity.getClassAddres();
        if (StringUtils.isEmpty(classAddress)){
            return setResultError("类路径名为空");
        }
        //3.2 使用反射机制初始化子类
        PayStrategy specificStrategy = StrategyFactory.getSpecificStrategy(classAddress);
        if (specificStrategy == null){
            return setResultError("没有找到对应的方法");
        }
        String html = specificStrategy.toHtml(paymentChannelEntity, payMentTransacDTOBaseResponse.getData());
        //4.将html返回
        JSONObject data = new JSONObject();
        data.put("html",html);
        return setResultSuccess(data);
    }
}
