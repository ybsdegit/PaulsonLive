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