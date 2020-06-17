package com.demo.pay.service.strategy;

import com.demo.pay.out.PayMentTransacDTO;

public interface PayStrategy {
    public String toHtml(String classAddress, PayMentTransacDTO payMentTransacDTO);
}
