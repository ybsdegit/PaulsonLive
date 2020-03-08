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
