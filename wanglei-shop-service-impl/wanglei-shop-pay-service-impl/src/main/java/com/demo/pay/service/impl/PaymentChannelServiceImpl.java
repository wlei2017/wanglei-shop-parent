package com.demo.pay.service.impl;

import com.demo.common.BaseApiService;
import com.demo.pay.mapper.PaymentChannelMapper;
import com.demo.pay.mapper.entity.PaymentChannelEntity;
import com.demo.pay.out.PaymentChannelDTO;
import com.demo.pay.service.PaymentChannelService;
import com.demo.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentChannelServiceImpl extends BaseApiService<List<PaymentChannelDTO>> implements PaymentChannelService {
    @Autowired
    private PaymentChannelMapper paymentChannelMapper;

    @Override
    public List<PaymentChannelDTO> selectAll() {
        List<PaymentChannelEntity> paymentChanneList = paymentChannelMapper.selectAll();
        return MapperUtils.mapAsList(paymentChanneList, PaymentChannelDTO.class);
    }
}
