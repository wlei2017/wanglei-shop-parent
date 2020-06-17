package com.demo.utils;

public class Snow {
    private volatile static SnowflakeIdWorker snowflakeIdWorker = null;
    public static SnowflakeIdWorker getSnowflakeIdWorker(){
        if (snowflakeIdWorker == null){
            synchronized (Snow.class){
                if (snowflakeIdWorker == null){
                    snowflakeIdWorker = new SnowflakeIdWorker(1,1);
                }
            }
        }
        return snowflakeIdWorker;
    }
}
