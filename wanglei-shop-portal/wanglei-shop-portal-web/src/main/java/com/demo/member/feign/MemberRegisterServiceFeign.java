package com.demo.member.feign;

import com.demo.member.service.MemberRegisterService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("app-mayikt-member")
public interface MemberRegisterServiceFeign extends MemberRegisterService {

}
