package com.ybs.state;

/**
 * TestDaemon
 * 守护线程
 * 上帝守护你
 * @author Paulson
 * @date 2020/3/9 0:09
 */
public class TestDaemon {

    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        thread.setDaemon(true);  // 默认是false 表示是用户线程
        thread.start();

        new Thread(you).start();
    }
}

class God implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("上帝保佑着你");
        }
    }
}

class You implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("你一生都开心的活着");
        }
        System.out.println("==== good bye ! world");
    }
}
