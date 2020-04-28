package com.demo.product.service.impl;

import com.demo.common.BaseApiService;
import com.demo.common.BaseResponse;
import com.demo.goods.service.ProductSearchService;
import com.demo.product.output.ProductOutDto;
import com.demo.product.service.es.ProductEntity;
import com.demo.product.service.reposiory.ProductReposiory;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class ProductSearchServiceImpl extends BaseApiService<List<ProductOutDto>> implements ProductSearchService {

    @Autowired
    private ProductReposiory productReposiory;
    @Override
    public BaseResponse<List<ProductOutDto>> search(String name) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        // 模拟查询
        builder.must(QueryBuilders.fuzzyQuery("name", name));
        Pageable pageable = new QPageRequest(0, 5);
        Page<ProductEntity> page = productReposiory.search(builder, pageable);
        List<ProductEntity> content = page.getContent();
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        List<ProductOutDto> mapAsList = mapperFactory.getMapperFacade().mapAsList(content, ProductOutDto.class);
        return setResultSuccess(mapAsList);
    }
}
