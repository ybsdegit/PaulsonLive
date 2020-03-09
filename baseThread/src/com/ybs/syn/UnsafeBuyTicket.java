package com.ybs.syn;

/**
 * UnsafeBuyTicket
 * 线程不安全
 * @author Paulson
 * @date 2020/3/9 0:21
 */

public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station, "我").start();
        new Thread(station, "你").start();
        new Thread(station, "他").start();
    }

}

class  BuyTicket implements Runnable{

    private int ticketNums = 10;
    boolean flag = true;  // 外部停止方式

    @Override
    public void run() {
        // 买票
        while (flag){
            buy();
        }
    }

    // 同步方法 锁的是this
    private synchronized void buy(){
        // 判断是否邮票
        if (ticketNums <= 0){
            flag = false;
            return;
        }
        // 买票
        ticketNums--;
        System.out.println(Thread.currentThread().getName() + "拿到" + ticketNums);
    }
}

