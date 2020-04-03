package com.skjilygao.test;

import java.util.Arrays;
import java.util.function.IntPredicate;

public class TemtTests {
    public static void main(String[] args) {
        // 根据给定的数组创建一棵树
        int[] arr = new int[]{5, 4, 8, 11};

//        Arrays.stream(arr).reduce(0, (x, y) -> x+y);
//        IntPredicate num = (int i)->i%100 == 0;
        System.out.println(Arrays.stream(arr).anyMatch(integer -> integer == 5));
        System.out.println( Arrays.binarySearch(arr, 5));
//        Arrays.binarySearch(arr, 5);
    }

    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = Arrays.stream(A).reduce(0, (x,y) -> x+y);
        int sumB = Arrays.stream(B).reduce(0, (x,y) -> x+y);

        for(int exchangeA : A){
            int exchangeB = getExchangeB(sumA,sumB, exchangeA);
            System.out.println("exchangeA="+exchangeA+", exchangeB="+exchangeB);
            if(Arrays.stream(B).anyMatch(i -> i == exchangeB)){
                // if(Arrays.stream(B).anyMatch(exchangeB)){
                return new int[]{exchangeA, exchangeB};
            }
        }
        return new int[]{0, 0};

    }

    private int getExchangeB(int sumA, int sumB, int exchangeA){
        int exchangeB = (sumB - sumA) / 2 + exchangeA;
        return exchangeB;
    }
}
