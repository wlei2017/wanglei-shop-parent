package com.demo.product.service.impl;

import com.demo.goods.service.AopTestInterface;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopTestImpl implements AopTestInterface {
    @Override
    public String getName(String name) {
        return name + "haha";
    }
}
