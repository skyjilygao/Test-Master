package com.skjilygao.test;

import java.util.ArrayList;

/**
 * 参考：https://juejin.im/post/5b55660ee51d4519202e2003
 * <p> 快速排序：使用分治法策略来把一个序列分为两个子序列
 * <br> 1. 先从序列中取出一个数作为基准数；
 * <br> 2. 分区过程：将把这个数大的数全部放到它的右边，小于或者等于它的数全放到它的左边；
 * <br> 3. 递归地对左右子序列进行不走2，直到各区间只有一个数
 * @author skyjilygao
 * @since 20190822
 *
 */
public class QuickSort {

    /**
     * 下标i,j的值相互交换
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void printArr(int[] arr) {
        for (int anArr : arr) {
            System.out.print(anArr + " ");
        }
    }

    /**
     * 分区
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partition(int[] arr, int left, int right) {
        // 采用三数中值分割法
        int mid = left + (right - left) / 2;
        // 保证左端较小
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }
        // 保证中间较小
        if (arr[mid] > arr[right]) {
            swap(arr, mid, right);
        }
        // 保证中间最小，左右最大
        if (arr[mid] > arr[left]) {
            swap(arr, left, mid);
        }
        int pivot = arr[left];
        while (right > left) {
            // 先判断基准数和后面的数依次比较
            while (pivot <= arr[right] && left < right) {
                --right;
            }
            // 当基准数大于了 arr[right]，则填坑
            if (left < right) {
                arr[left] = arr[right];
                ++left;
            }
            // 现在是 arr[right] 需要填坑了
            while (pivot >= arr[left] && left < right) {
                ++left;
            }
            if (left < right) {
                arr[right] = arr[left];
                --right;
            }
        }
        arr[left] = pivot;
        printArr(arr);
        System.out.println("\n");
        return left;
    }

    /**
     * 快排入口
     * @param arr
     * @param left
     * @param right
     */
    private static void quickSort(int[] arr, int left, int right) {
        if (arr == null || left >= right || arr.length <= 1) {
            return;
        }
        int mid = partition(arr, left, right);
        quickSort(arr, left, mid);
        System.out.println("\n sort first----: ");
        printArr(arr);
        quickSort(arr, mid + 1, right);
    }


    public static void main(String[] args) {
        int[] arr = {6, 4, 3, 2, 7, 9, 1, 8, 5};
        System.out.println("sort before: ");
        printArr(arr);
        System.out.println("\nsort start: ");
        quickSort(arr, 0, arr.length - 1);
        printArr(arr);
    }
}