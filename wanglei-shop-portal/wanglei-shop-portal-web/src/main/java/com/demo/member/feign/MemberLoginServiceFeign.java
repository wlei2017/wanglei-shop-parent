package com.demo.member.feign;

import com.demo.member.service.MemberLoginService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("app-mayikt-member")
public interface MemberLoginServiceFeign extends MemberLoginService {

}
