package com.demo.goods.service;

import com.demo.common.BaseResponse;
import com.demo.product.output.ProductOutDto;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ProductSearchService {
    @GetMapping("/search")
    public BaseResponse<List<ProductOutDto>> search(String name);
}
