package com.ybs.algorithm.sort;

/**
 * Selection
 * 选择排序
 *
 * @author Paulson
 * @date 2020/4/11 22:15
 */
public class Selection {

    /**
     * 对数组a中的元素进行排序
     *
     * @param a
     */
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length - 1 - 1; i++) {
            // 定义一个变量，记录最小元素所在的索引 默认为第一个元素的索引
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (greater(a[minIndex], a[j])) {
                    minIndex = j;
                }
            }
            // 交换最小索引处的值和索引处的值
            exchange(a, i, minIndex);
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
