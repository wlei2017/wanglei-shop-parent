package com.demo.pay.service.factory;

import com.demo.pay.service.strategy.PayStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StrategyFactory {
    private static Map<String,PayStrategy> map = new ConcurrentHashMap<String, PayStrategy>();
    public static PayStrategy getSpecificStrategy(String classAddress){
        try {
            if (map.get(classAddress) != null){
                return map.get(classAddress);
            }
            Class<?> aClass  = Class.forName(classAddress);
            PayStrategy payStrategy = (PayStrategy) aClass.newInstance();
            map.put(classAddress,payStrategy);
            return payStrategy;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
