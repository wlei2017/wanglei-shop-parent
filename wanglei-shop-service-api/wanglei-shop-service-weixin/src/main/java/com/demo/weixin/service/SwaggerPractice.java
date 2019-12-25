package com.demo.weixin.service;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Api(value = "swagger练习",tags = "有参接口")
public interface SwaggerPractice {
    @GetMapping("/testSwagger")
    @ApiOperation(value = "swagger练习",notes = "输入两个参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "姓名",required = true),
            @ApiImplicitParam(name = "des", value = "描述", required = false)}
    )


    public String testSwagger(@ApiParam(name = "name",value = "姓名",required = true)String a,
                              @ApiParam(name = "des", value = "描述", required = false)String b);
}
