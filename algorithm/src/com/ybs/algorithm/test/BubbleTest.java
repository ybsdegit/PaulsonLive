package com.ybs.algorithm.test;

import com.ybs.algorithm.sort.Bubble;

import java.util.Arrays;

/**
 * BubbleTest
 *
 * @author Paulson
 * @date 2020/4/11 22:04
 */
public class BubbleTest {
    public static void main(String[] args) {
        Integer[] arr = {4, 5, 6, 3, 2, 1};
        Bubble.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
