package com.demo.goods.service;

import org.springframework.web.bind.annotation.GetMapping;

public interface AopTestInterface {
    @GetMapping("/hello")
    public String getName(String name);
}
