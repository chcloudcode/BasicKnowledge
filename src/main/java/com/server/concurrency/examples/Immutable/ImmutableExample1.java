package com.server.concurrency.examples.Immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 不可变对象测试包
 */
@Slf4j
public class ImmutableExample1 {

    private final static Integer a = 7;

    private final static String b = "b";

    private final static Map<Integer,Integer> map = Maps.newHashMap();

    static{
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {
        map.put(1,10);
        log.info("{}",map.get(1));
    }

}
