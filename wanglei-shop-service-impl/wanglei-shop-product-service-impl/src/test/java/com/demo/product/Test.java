package com.demo.product;

import org.apache.commons.lang.StringUtils;

public class Test {
    @org.junit.Test
    public void testaa(){
        int a = 1;
        String s = "aad";
        if (! StringUtils.isEmpty(s) && a==1){
            System.out.println("kk");
        }else {
            System.out.println("aa");
        }
        
    }
}
