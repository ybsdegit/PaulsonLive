package com.ybs.single;

import java.time.OffsetDateTime;

/**
 * LazyMain
 * 懒汉式单例
 *
 * @author Paulson
 * @date 2020/4/15 14:05
 */
public class LazyMain {

    private static boolean ybs = false;

    private LazyMain() {
        synchronized (LazyMain.class){
            if (ybs == false){
                ybs = true;
            }else {
                throw new RuntimeException("不要试图使用反射破坏异常");
            }
        }
        System.out.println(Thread.currentThread().getName() + "ok");
    }

    private volatile static LazyMain lazyMain;

    /**
     * 双重检测锁模式的 懒汉式单例  DCL懒汉式
     * @return
     */
    public static LazyMain getInstance(){
        if (lazyMain == null){
            synchronized (LazyMain.class){
                if (lazyMain == null){
                    lazyMain = new LazyMain();
                    // 不是一个原子性操作
                    /**
                     * 1、分配内存空间
                     * 2、执行构造方法，初始化对象
                     * 3、把这个对象指向这个空间
                     *
                     * ，没有完成构造指令重拍
                     */
                }
            }
        }
        return lazyMain;
    }

    public static void main(String[] args) {
        // 多线程并发下 懒汉式到单例会有问题，需要加锁
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                LazyMain.getInstance();
            }).start();
        }
    }
}
