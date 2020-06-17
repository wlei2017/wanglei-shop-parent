package com.demo.pay.service;

import com.demo.common.BaseResponse;
import com.demo.pay.out.PayMentTransacDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface PayMentTransacInfoService {
    @GetMapping("/tokenByPayMentTransac")
    public BaseResponse<PayMentTransacDTO> tokenByPayMentTransac(@RequestParam("token") String token);
}
