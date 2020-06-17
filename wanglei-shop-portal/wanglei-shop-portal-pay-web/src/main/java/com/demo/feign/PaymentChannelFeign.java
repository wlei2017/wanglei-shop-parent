package com.demo.feign;

import com.demo.pay.service.PaymentChannelService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-mayikt-pay")
public interface PaymentChannelFeign extends PaymentChannelService {
}
