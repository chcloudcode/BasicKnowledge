package com.server.DesignPattern.ProxyPattern.DynamicProxyExample;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HumanProxy {

    private Object object;

    public HumanProxy(Object target){
        this.object=target;
    }

    public Object getHumanProxy(){
       return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new InvocationHandler() {
           @Override
           public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               System.out.println("动态代理对象 proxyTarget");

               if(object.getClass()==Man.class){
                   System.out.println("i am man ,please find a women for me ");
               }
               if(object.getClass()==Women.class){
                   System.out.println("i am women, please find a man for me ");
               }
               if(method.getName().equals("color")){
                   System.out.println("我们在讨论男人和女人的肤色");
               }
               Object result = method.invoke(object,args);
               System.out.println("目标方法执行完了");
               System.out.println("代理对象活儿干完了");
               return result;
           }
       });
    }
}
