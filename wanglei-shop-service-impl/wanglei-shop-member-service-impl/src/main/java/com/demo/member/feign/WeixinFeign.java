package com.demo.member.feign;


import com.demo.weixin.service.GetAppOfWeixin;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "app-mayikt-weixin")
public interface WeixinFeign extends GetAppOfWeixin {

}
