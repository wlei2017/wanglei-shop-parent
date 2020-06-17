package com.demo.utils;

public class Snow1 {
    private static class LazyHolder{
        private static final SnowflakeIdWorker SNOWFLAKE_ID_WORKER = new SnowflakeIdWorker(1,1);
        public static SnowflakeIdWorker getSnowflakeIdWorker(){
            return LazyHolder.SNOWFLAKE_ID_WORKER;
        }
    }
}
