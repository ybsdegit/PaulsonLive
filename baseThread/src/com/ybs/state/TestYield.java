package com.ybs.state;

/**
 * TestYield
 * 礼让不一定成功
 * @author Paulson
 * @date 2020/3/8 23:51
 */
public class TestYield {

    public static void main(String[] args) {
        MyYield myYield = new MyYield();
        new Thread(myYield, "a").start();
        new Thread(myYield, "b").start();
    }


}

class MyYield implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始运行");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "线程停止运行");
    }
}
