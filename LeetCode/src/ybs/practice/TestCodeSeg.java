package ybs.practice;

/**
 * TestCodeSeg
 *
 * @author Paulson
 * @date 2020/4/8 20:22
 */
public class TestCodeSeg {

    /**
     * 静态代码块第一时间执行
     */
    static {
        System.out.println("1");
    }

    /**
     * 对象代码块，创建对象的时候会执行
     */
    {
        System.out.println("2");
    }

    /**
     * 构造器
     */

    public TestCodeSeg(){
        /* System.out.println("3"); */   // 1,2,3
        System.err.println("3");  // 1, 2, 3     // 312
    }

    public static void main(String[] args) {
        new TestCodeSeg();
    }
}
