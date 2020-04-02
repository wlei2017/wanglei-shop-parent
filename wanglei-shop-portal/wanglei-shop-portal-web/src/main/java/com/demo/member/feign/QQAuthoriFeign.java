package com.demo.member.feign;

import com.demo.member.service.QQAuthoriService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-mayikt-member")
public interface QQAuthoriFeign extends QQAuthoriService {
}
