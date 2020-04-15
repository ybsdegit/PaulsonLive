package com.ybs.algorithm.test;

import com.ybs.algorithm.sort.Insertion;

import java.util.Arrays;

/**
 * SelectionTest
 *
 * @author Paulson
 * @date 2020/4/11 22:22
 */
public class InsertionTest {
    public static void main(String[] args) {
        Integer[] arr = {4, 5, 6, 3, 2, 1};
        Insertion.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
