package com.demo.weixin.service.impl;

import com.demo.weixin.service.SwaggerPractice;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwaggerPracticeImpl implements SwaggerPractice {
    @Override
    public String testSwagger(String a, String b) {

        return a + b;
    }
}
