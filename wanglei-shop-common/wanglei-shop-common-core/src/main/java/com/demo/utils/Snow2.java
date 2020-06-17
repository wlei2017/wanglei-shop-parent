package com.demo.utils;

public enum Snow2 {
INSTANCE;
public SnowflakeIdWorker getSnow(){
    return new SnowflakeIdWorker(1,1);
}

}
