package com.server.Basic.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class TestAnnotation {

    public static void main(String[] args) {

        // 1. 使用类加载器加载类
        try {
            Class c = Class.forName("com.server.Basic.annotation.Child");

            // 2. 找到类上的注解
            Boolean isExists = c.isAnnotationPresent(Description.class);
            if(isExists){
                //3. 获取注解实例
                Description d = (Description) c.getAnnotation(Description.class);
                System.out.println(d.value());
            }

            // 4. 找到方法上的注解
            Method[] ms = c.getMethods();
            for(Method method : ms){
                Boolean isMeExists = method.isAnnotationPresent(Description.class);
                if(isMeExists){
                    Description d = method.getAnnotation(Description.class);
                    System.out.println(d.value());
                }
            }

            // 5. 方法注解的另外一种解析
            for(Method each : ms){
                Annotation[] all =  each.getAnnotations();
                for( Annotation a : all){
                    if(a instanceof Description){
                        System.out.println(((Description) a).value());
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
