package com.bangdi.reflect;

import com.bangdi.entity.Emp;

public class CreateClassObject {
    public static void main(String[] args) throws ClassNotFoundException {
        //通过class.forname()来获取对象
        Class clazz = Class.forName("com.bangdi.entity.Emp");
        System.out.println(clazz.getPackage());
        System.out.println(clazz.getName());
        System.out.println(clazz.getSimpleName());
        System.out.println(clazz.getCanonicalName());
        System.out.println("-------------------------");


        //通过类名.class获取
        Class empClass = Emp.class;
        System.out.println(clazz.getPackage());
        System.out.println(clazz.getName());
        System.out.println(clazz.getSimpleName());
        System.out.println(clazz.getCanonicalName());
        System.out.println("-------------------------");


    }
}
