package com.bangdi.test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test01 {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date parse = simpleDateFormat.parse("2019-07-06");
        long time = parse.getTime();
        System.out.println(time);
    }
}
