# Java多线程
- 线程创建
    - 继承Thread
    - 实现Runnable
    - 实现Callable


### 创建线程方式 一： 继承 Thread，重写run方法
- 实现
```java
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

```
- 实例
```java

package com.ybs.demo01;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * testThread2
 * 实现多线程同步下载图片
 * @author Paulson
 * @date 2020/3/8 3:37
 */
public class TestThread2 extends Thread {

    private String url;
    private String name;

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    // 下载图片的执行体
    @Override
    public void run() {
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.downLoader(url, name);
        System.out.println("下载了文件名为："+ name);
    }

    public static void main(String[] args) {
        String url = "https://gss0.bdstatic.com/70cFfyinKgQIm2_p8IuM_a/daf/pic/item/a8773912b31bb051ec146159397adab44aede08b.jpg";
        String name = "1.jpg";

        TestThread2 t1 = new TestThread2(url, "1.jpg");
        TestThread2 t2 = new TestThread2(url, "2.jpg");
        TestThread2 t3 = new TestThread2(url, "3.jpg");

        t1.start();
        t2.start();
        t3.start();
    }
}

class WebDownLoader{
    public void downLoader(String url, String name){
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO 异常， downLoader方法出现问题");
        }
    }
}
```

![f04bee75e7e4bd5f04562668df900b2c.png](en-resource://database/10166:1)

### 创建线程方式 二： 实现runnable接口，重写run方法。执行线程需要丢入runnable接口实现类调用start方法

```java

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

```


#### 多个线程同时操作同一个对象
- 推荐使用 runnable接口实现多线程能力。
- 避免单继承的局限性，灵活方便，方便同一个对象被多个线程使用

```java
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

```

### 创建线程方式 三 实现Callable接口（有返回值）

- 可以抛出异常
- 可以有返回值
```java
package com.ybs.deme02;

import com.ybs.demo01.TestThread2;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * TestCallable
 * 创建线程方式 三 实现Callable接口（有返回值）
 *
 * @author Paulson
 * @date 2020/3/8 22:19
 */
public class TestCallable implements Callable<Boolean> {

    private String url;
    private String name;

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    // 下载图片的执行体
    @Override
    public Boolean call() {
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.downLoader(url, name);
        System.out.println("下载了文件名为："+ name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String url = "https://gss0.bdstatic.com/70cFfyinKgQIm2_p8IuM_a/daf/pic/item/a8773912b31bb051ec146159397adab44aede08b.jpg";
        String name = "1.jpg";

        TestCallable t1 = new TestCallable(url, "1.jpg");
        TestCallable t2 = new TestCallable(url, "2.jpg");
        TestCallable t3 = new TestCallable(url, "3.jpg");

        // 创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);

        // 提交执行
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);

        // 获取结果
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();

        // 关闭服务
        ser.shutdown();
    }
}

class WebDownLoader{
    public void downLoader(String url, String name){
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO 异常， downLoader方法出现问题");
        }
    }
}

```


### 线程五大状态
- 创建 new
- 就绪 start
- 阻塞 sleep wait
- 运行
- 死亡 dead
#### 停止线程： 建议使用一个标志位进行终止变量
* 1、建议线程正常停止 --- 》 利用次数，不建议死循环
* 2、建议使用标志位
* 3、不要使用stop 或者 destroy 等过时的方法或者JDK不建议使用的方法


```java
package com.ybs.state;

/**
 * TestStop
 * 1、建议线程正常停止 --- 》 利用次数，不建议死循环
 * 2、建议使用标志位
 * 3、不要使用stop 或者 destroy 等过时的方法或者JDK不建议使用的方法
 * @author Paulson
 * @date 2020/3/8 23:34
 */
public class TestStop implements Runnable {

    // 设置一个标志位
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag){
            System.out.println("run ... Thread " + i++);
        }
    }

    // 2、 设置一个公开的方法停止线程
    public void stop(){
        this.flag = false;
    }

    public static void main(String[] args) {

        TestStop testStop = new TestStop();
        new Thread(testStop).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("main--》"+i);

            if (i == 900){
                testStop.stop();
                System.out.println("线程停止了");
            }
        }
    }
}

```

#### 线程休眠 sleep

- 每一个对象都有一个锁，sleep不会释放锁

```java
package com.ybs.state;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TestSleep
 * 模拟倒计时
 * @author Paulson
 * @date 2020/3/8 23:44
 */
public class TestSleep {

    public static void main(String[] args) throws InterruptedException {
//        tenDown();
        // 打印当前系统时间
        Date startTime = new Date(System.currentTimeMillis());
        while (true){
            Thread.sleep(1000);
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
            startTime = new Date(System.currentTimeMillis());
        }
    }

    // 模拟倒计时
    public static void tenDown() throws InterruptedException {
        int num = 10;
        while (true){
            Thread.sleep(1000);
            System.out.println(num--);
            if (num <=0){
                break;
            }
        }
    }
}

```


#### 线程礼让 yield
- 礼让线程，让当前正在执行的线程暂停，但不阻塞
- 将线程从运行状态转为就绪状态
- 让cpu重新调度，礼让不一定成功

```java
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

```

#### 线程合并 Join
- Join 合并线程， 插队
```java
package com.ybs.state;

/**
 * TestJion
 * 插队
 * @author Paulson
 * @date 2020/3/8 23:56
 */
public class TestJoin implements  Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            System.out.println("vip==》" +i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();


        for (int i = 0; i < 500; i++) {
            if (i==200){
                thread.join();
            }
            System.out.println("main ==> "+i);
        }
    }
}

```

#### 线程状态观测
Thread.state

#### 线程优先级
范围 1~10

- 改变优先级
    `getPriority()` `setPriority(int XXX)`


#### 守护（daemon）线程
- 虚拟机必须确保用户线程执行完毕
- 虚拟机不用等待守护线程执行完毕
- 后台记录日志，监控内存，垃圾回收等待

```java
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

```


### 线程同步 多个线程操作同一个资源
synchronized
- 并发
- 队列和锁  解决线程安全问题

#### 线程不安全实例
- 买票
```java
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

    private void buy(){
        // 判断是否邮票
        if (ticketNums <= 0){
            flag = false;
            return;
        }
        // 模拟延时
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 买票
        ticketNums--;
        System.out.println(Thread.currentThread().getName() + "拿到" + ticketNums);
    }
}

```

- 银行取钱

```java
package com.ybs.syn;

/**
 * UnsafeBank
 * 不安全的取钱
 * @author Paulson
 * @date 2020/3/10 1:39
 */
public class UnsafeBank {
    public static void main(String[] args) {
        Account account = new Account(100, "结婚基金");
        Drawing you = new Drawing(account, 50, "你");
        Drawing girl = new Drawing(account, 100, "girl");

        you.start();
        girl.start();
    }
}

// 账户
class Account{
    int money;
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

// 银行 模拟取款
class Drawing extends Thread {
    Account account;  // 账户
    int drawingMoney;  // 取了多少钱
    int nowMont;  // 现在手里有多少钱

    public Drawing(Account account, int drawingMoney, String name){
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    // 取钱
    @Override
    public void run() {
        // 判断有没有钱
        if (account.money - drawingMoney < 0){
            System.out.println(Thread.currentThread().getName()+"钱不够，去不了");
            return;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        account.money = account.money - drawingMoney;
        nowMont = nowMont + drawingMoney;
        System.out.println(account.name + "余额为:" + account.money);
        System.out.println(this.getName() + "手里的钱为:" + nowMont);
    }
}

```
- 不安全的列表
```java
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
                list.add(Thread.currentThread().getName());
            }).start();
        }
        System.out.println(list.size());
    }
}

```

#### 线程不安全解决方案
- 买票

```Java
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


```
- 取钱

```java
package com.ybs.syn;

/**
 * UnsafeBank
 * 不安全的取钱
 * @author Paulson
 * @date 2020/3/10 1:39
 */
public class UnsafeBank {
    public static void main(String[] args) {
        Account account = new Account(100, "结婚基金");
        Drawing you = new Drawing(account, 50, "你");
        Drawing girl = new Drawing(account, 100, "girl");

        you.start();
        girl.start();
    }
}

// 账户
class Account{
    int money;
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

// 银行 模拟取款
class Drawing extends Thread {
    Account account;  // 账户
    int drawingMoney;  // 取了多少钱
    int nowMont;  // 现在手里有多少钱

    public Drawing(Account account, int drawingMoney, String name){
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    // 取钱
    @Override
    public void run() {
        synchronized (account){
            // 判断有没有钱
            if (account.money - drawingMoney < 0){
                System.out.println(Thread.currentThread().getName()+"钱不够，去不了");
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.money = account.money - drawingMoney;
            nowMont = nowMont + drawingMoney;
            System.out.println(account.name + "余额为:" + account.money);
            System.out.println(this.getName() + "手里的钱为:" + nowMont);
        }

    }
}

```
- 集合
```java
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

```
### 死锁
1、一个资源只能被一个进程使用
2、一个进程请求阻塞时对已获得的资源保持不放
3、不剥夺
4、循环等待
- 避免死亡的方法，避免上面情况的发生

### lock锁 JUC
- 显示的定义同步锁

```java
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

```

### 线程通信
- wait（） 线程一直等待，直到其他线程通知
- wait（long timeout）执行等待的毫秒数
- notify 唤醒一个处于等待状态的线程
- notifyAll唤醒一个对象上所有调用wait方法的线程，优先级别高的线程有限调度

##### 生产者消费者
- 管程法 利用缓冲区解决 wait notify
- 信号灯法 标志位

### 线程池
- 创建好多个线程，放入线程池中。避免线程经常创建和销毁。

```java
ExecutorService service = Executors.newFixedThreadPool(10)
```

