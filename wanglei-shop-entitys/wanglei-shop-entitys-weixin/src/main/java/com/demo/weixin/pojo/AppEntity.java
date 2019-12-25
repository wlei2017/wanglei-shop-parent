package com.demo.weixin.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "app实体类",description = "简单的实体类")
public class AppEntity {
    private String id;
    private String name;

    public AppEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
