package com.ybs.single;

import org.omg.PortableInterceptor.HOLDING;

/**
 * Holder
 * 静态内部类
 *
 * @author Paulson
 * @date 2020/4/15 14:13
 */
public class Holder {
    private Holder(){

    }

    public static Holder getInstance(){
        return InnerClass.HOLDER;
    }

    public static class InnerClass {
        private static final Holder HOLDER = new Holder();
    }
}
