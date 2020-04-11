package com.ybs.algorithm.test;

import com.ybs.algorithm.sort.Student;

/**
 * TestComparable
 *
 * @author Paulson
 * @date 2020/4/11 21:31
 */

public class TestComparable {

    public static void main(String[] args) {
        Student s1 = new Student("张三", 16);
        Student s2 = new Student("李四", 18);

        Comparable max = getMax(s1, s2);
        System.out.println(max);
    }

    public static Comparable getMax(Comparable c1, Comparable c2){
        int result = c1.compareTo(c2);
        // 如果result < 0 ,则 c1<c2
        // 如果result > 0 ,则 c1>c2
        // 如果result = 0 ,则 c1=c2
        if (result >= 0){
            return c1;
        }else {
            return c2;
        }
    }
}
