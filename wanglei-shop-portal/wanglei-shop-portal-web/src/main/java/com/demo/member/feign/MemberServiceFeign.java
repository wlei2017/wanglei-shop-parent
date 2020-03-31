package com.demo.member.feign;

import com.demo.member.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("app-mayikt-member")
public interface MemberServiceFeign extends MemberService {

}
