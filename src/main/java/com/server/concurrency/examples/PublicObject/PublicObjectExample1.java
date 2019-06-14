package com.server.concurrency.examples.PublicObject;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 不安全的发布对象 : 因为无法确定其他线程是否会修改这个对象里面的内容
 */
@Slf4j
public class PublicObjectExample1 {

    private String[]  status =  {"a","b","c"};

    public String[] getStatus(){
        return status;
    }

    public static void main(String[] args) {
        PublicObjectExample1 example1 = new PublicObjectExample1();
        log.info("{}", Arrays.toString(example1.getStatus()));

        example1.getStatus()[0]="d";
        log.info("{}", Arrays.toString(example1.getStatus()));

    }

}
