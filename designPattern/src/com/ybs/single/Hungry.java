package com.ybs.single;

/**
 * Hungry
 * 饿汉式单例
 * @author Paulson
 * @date 2020/4/15 14:00
 */
public class Hungry {

    // 可能会浪费空间

    private Hungry() {}

    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance(){
        return HUNGRY;
    }
}
