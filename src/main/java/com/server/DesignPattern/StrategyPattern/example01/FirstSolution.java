package com.server.DesignPattern.StrategyPattern.example01;

/**
 * 三个具体的锦囊，如何解决问题
 */
public class FirstSolution implements Solution {
    @Override
    public void solve() {
        System.out.println("第一个锦囊：找乔国老帮忙，让吴国太给孙权施加压力！");
    }
}
