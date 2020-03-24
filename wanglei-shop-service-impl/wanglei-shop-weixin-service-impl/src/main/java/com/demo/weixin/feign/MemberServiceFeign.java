package com.demo.weixin.feign;

import com.demo.member.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "app-mayikt-member")
public interface MemberServiceFeign extends MemberService {
}
