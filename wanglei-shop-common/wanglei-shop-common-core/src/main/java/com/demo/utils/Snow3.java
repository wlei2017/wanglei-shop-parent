package com.demo.utils;

public class Snow3 {
    public static void main(String[] args) throws Exception {
//        SnowflakeIdWorker snow = Snow2.INSTANCE.getSnow();
//        Constructor<Snow2> con = Snow2.class.getDeclaredConstructor();
//        con.setAccessible(true);
//        Snow2 snow2 = con.newInstance();
//        Snow2 snow21 = con.newInstance();
//        System.out.println(snow2.equals(snow21));

//        Constructor<Snow> con = Snow.class.getDeclaredConstructor();
//        con.setAccessible(true);
//        Snow snow =(Snow) con.newInstance();
//        Snow snow1 =(Snow) con.newInstance();
//        System.out.println(snow.equals(snow1));

//        Constructor<Snow1> con = Snow1.class.getDeclaredConstructor();
//        con.setAccessible(true);
//        Snow1 snow1 = con.newInstance();
//        Snow1 snow2 = con.newInstance();
//        System.out.println(snow1.equals(snow2));

        String s = SnowflakeIdUtils.nextId();
        System.out.println(s);
    }
}
