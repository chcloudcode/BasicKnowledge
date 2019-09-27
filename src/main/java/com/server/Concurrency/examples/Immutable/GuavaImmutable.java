package com.server.Concurrency.examples.Immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class GuavaImmutable {

    private final static ImmutableList list = ImmutableList.of(1,2,3,4,5,6);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer,Integer> map = ImmutableMap.of(1,2,3,4,5,6);

    public static void main(String[] args) {
        list.add(4);
        map.put(7,8);
    }

}
