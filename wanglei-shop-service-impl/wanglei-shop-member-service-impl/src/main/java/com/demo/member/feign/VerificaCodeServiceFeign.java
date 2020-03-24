package com.demo.member.feign;

import com.demo.weixin.service.VerifyCodeService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "app-mayikt-weixin")
public interface VerificaCodeServiceFeign extends VerifyCodeService {
}
