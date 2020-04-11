package com.ybs.algorithm.sort;


/**
 * Bubble
 * 冒泡排序
 *
 * @author Paulson
 * @date 2020/4/11 21:51
 */
public class Bubble {

    /**
     * 对数组a中的元素进行排序
     *
     * @param a
     */
    public static void sort(Comparable[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (greater(a[j], a[j + 1])) {
                    exchange(a, j, j + 1);
                }
            }
        }
    }

    /**
     * 比较v元素是否大于w元素
     *
     * @param v
     * @param w
     * @return
     */
    public static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    /**
     * 数据元素i和j交换位置
     *
     * @param a 数组
     * @param i i
     * @param j j
     */
    public static void exchange(Comparable[] a, int i, int j) {
        Comparable temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
