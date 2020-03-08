package com.ybs.demo01;

/**
 * TestThread3
 * 创建线程方式 二： 实现runnable接口，重写run方法。执行线程需要丢入runnable接口实现类调用start方法
 * @author Paulson
 * @date 2020/3/8 21:48
 */
public class TestThread3 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("我是子线程"+i);
        }
    }

    public static void main(String[] args) {

        // 创建runnable接口的实现类对象
        TestThread3 testThread3 = new TestThread3();

        // 创建线程对象，通过线程对象来开启线程，代理
       /* Thread thread = new Thread(testThread3);
        thread.start();*/

        new Thread(testThread3).start();


        for (int i = 0; i < 200; i++) {
            System.out.println("我是主线程"+i);
        }
    }
}
