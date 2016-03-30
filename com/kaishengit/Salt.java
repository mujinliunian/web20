package com.kaishengit;

import org.apache.commons.codec.digest.DigestUtils;

public class Salt {
    public static void main(String[] args) {
        String nam ="123456";
        String salt ="!#$%^&*&(){}()&^&%^%^";
        String str = DigestUtils.sha1Hex(nam+salt);
        System.out.println(str);
    }
}
