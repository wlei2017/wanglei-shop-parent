package com.demo.pay.service.strategy;

import com.demo.pay.mapper.entity.PaymentChannelEntity;
import com.demo.pay.out.PayMentTransacDTO;

public interface PayStrategy {
    public String toHtml(PaymentChannelEntity paymentChannelEntity, PayMentTransacDTO payMentTransacDTO);
}
