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
