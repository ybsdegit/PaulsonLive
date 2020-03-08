package com.ybs.demo01;

/**
 * TestThread4
 * 多个线程同时操作同一个对象
 * 买火车票
 * @author Paulson
 * @date 2020/3/8 21:57
 */

// 发现问题： 多个线程槽筒同一个资源的时候出现错乱(并发问题)
public class TestThread4 implements Runnable{
    private int ticketNums = 10;

    @Override
    public void run() {
        while (true){
            if (ticketNums <= 0){
                break;
            }

            // 模拟延时
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println( Thread.currentThread().getName()
                    +"拿到了票号为: "
                    + ticketNums--);
        }
    }

    public static void main(String[] args) {
        TestThread4 ticket = new TestThread4();
        new Thread(ticket, "小明").start();
        new Thread(ticket, "老师").start();
        new Thread(ticket, "我").start();
    }
}
