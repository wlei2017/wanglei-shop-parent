package com.xxl.sso.server.feign;

import com.demo.member.service.ServerLoginService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "app-mayikt-member")
public interface ServerLoginServiceFeign extends ServerLoginService {
}
