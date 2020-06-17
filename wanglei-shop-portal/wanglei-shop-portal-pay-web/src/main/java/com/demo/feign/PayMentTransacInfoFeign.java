package com.demo.feign;

import com.demo.pay.service.PayMentTransacInfoService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-mayikt-pay")
public interface PayMentTransacInfoFeign extends PayMentTransacInfoService {
}
