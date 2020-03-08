package com.ybs.demo01;

/**
 * TestThread1
 * 创建线程方式 一： 继承 Thread，重写run方法
 * @author Paulson
 * @date 2020/3/8 3:26
 * 总结：注意：线程开启不一定立即执行，由CPU调度执行
 */

public class TestThread1 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("我是子线程"+i);
        }
    }

    public static void main(String[] args) {

        // 创建一个线程对象，调用start方法开启线程
        TestThread1 testThread1 = new TestThread1();
        testThread1.start();
        // main 线程
        for (int i = 0; i < 200; i++) {
            System.out.println("我是主线程"+i);
        }
    }
}
