package com.ybs.syn;

import java.util.ArrayList;

/**
 * UnsafeList
 * 不安全的集合
 * @author Paulson
 * @date 2020/3/10 1:55
 */
public class UnsafeList {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(() ->{
                synchronized (list){
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
