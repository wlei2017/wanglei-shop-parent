package com.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableSwagger2Doc
@EnableEurekaClient
@EnableZuulProxy
@EnableApolloConfig
public class AppGateWay {

    @ApolloConfig
    private Config appConfig;

    public static void main(String[] args) {
        SpringApplication.run(AppGateWay.class,args);
    }

    @Component
    @Primary //自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
    class DocumentionConfig implements SwaggerResourcesProvider{

        @Override
        public List<SwaggerResource> get() {
//            List resources = new ArrayList();
//            resources.add(swaggerResources("app-mayikt-member","/app-mayikt-member/v2/api-docs","2.0"));
//            resources.add(swaggerResources("app-mayikt-weixin","/app-mayikt-weixin/v2/api-docs","2.0"));
//            return resources;
            return resources();
        }

        private List<SwaggerResource> resources() {

            List resources = new ArrayList<>();
            // app-itmayiedu-order
            // 网关使用服务别名获取远程服务的SwaggerApi
            String swaggerDocJson = swaggerDocument();
            JSONArray jsonArray = JSONArray.parseArray(swaggerDocJson);
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                String name = jsonObject.getString("name");
                String location = jsonObject.getString("location");
                String version = jsonObject.getString("version");
                resources.add(swaggerResources(name, location, version));
            }
            return resources;
        }

        /**
         * 获取swaggerDocument配置
         *
         * @return
         */
        private String swaggerDocument() {
            String property = appConfig.getProperty("wanglei-swagger-resources", "");
            return property;
        }
        private SwaggerResource swaggerResources(String name,String location,String version) {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion(version);
            return swaggerResource;
        }


    }
}
