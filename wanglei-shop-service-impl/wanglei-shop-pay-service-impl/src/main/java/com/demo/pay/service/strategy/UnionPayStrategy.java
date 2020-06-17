package com.demo.pay.service.strategy;

import com.demo.pay.out.PayMentTransacDTO;
import org.springframework.stereotype.Component;

@Component
public class UnionPayStrategy implements PayStrategy {
    @Override
    public String toHtml(String classAddress, PayMentTransacDTO payMentTransacDTO) {

        return "银联支付";
    }
}
