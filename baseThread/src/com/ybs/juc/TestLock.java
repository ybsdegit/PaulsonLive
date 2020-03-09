package com.ybs.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * TestLock
 *
 * @author Paulson
 * @date 2020/3/10 2:20
 */
public class TestLock {
    public static void main(String[] args) {
        TestLock2 testLock2 = new TestLock2();
        new Thread(testLock2).start();
        new Thread(testLock2).start();
        new Thread(testLock2).start();
    }
}

class TestLock2 implements Runnable{

    int ticketNums = 10;

    // 定义lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            lock.lock(); //加锁

            try {
                if (ticketNums > 0){
                    System.out.println(ticketNums--);
                }else {
                    break;
                }
            }finally {
                lock.unlock();
            }

        }

    }
}
