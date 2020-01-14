package com.demo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class MyCommandLineRunner implements CommandLineRunner {
    @ApolloConfig
    private Config config;
    private static final Logger logger = LoggerFactory.getLogger(MyCommandLineRunner.class);
    @Override
//    @ApolloConfigChangeListener
    public void run(String... args) throws Exception {
        config.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent configChangeEvent) {
                logger.info("##########监听成功#########"+configChangeEvent.changedKeys().toString());
            }
        });
    }
}
