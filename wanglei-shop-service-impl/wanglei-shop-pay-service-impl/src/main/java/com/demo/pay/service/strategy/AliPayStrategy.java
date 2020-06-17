package com.demo.pay.service.strategy;

import com.demo.pay.out.PayMentTransacDTO;
import org.springframework.stereotype.Component;

@Component
public class AliPayStrategy implements PayStrategy
{
    @Override
    public String toHtml(String classAddress, PayMentTransacDTO payMentTransacDTO) {
        return "支付宝支付";
    }
}
