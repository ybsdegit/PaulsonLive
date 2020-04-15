package com.ybs.algorithm.sort;

/**
 * Insertion
 * 插入排序
 *
 * @author Paulson
 * @date 2020/4/15 13:18
 */
public class Insertion {


    /**
     * 对数组a中的元素进行排序
     *
     * @param a
     */
    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                // 比较索引j处的值和索引j-1处的值
                if (greater(a[j - 1], a[j])) {
                    exchange(a, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }


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
