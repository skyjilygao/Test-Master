package com.skjilygao.test;

/**
 * 二进制反转。返回反转后对应的整数
 * 参考：https://leetcode-cn.com/problems/reverse-bits/
 * @author
 * @since 20191122
 */
public class ReverseBits {
    public static void main(String[] args) {
        int n = 23;
        int i = 0, j = 7, retVal = 0, temp;
        while (i < j) {
            temp = n >> i;
            temp &= 1;
            temp <<= j;
            retVal |= temp;
            temp = n >> j;
            temp &= 1;
            temp <<= i;
            retVal |= temp;
            i++;
            j--;
        }
        System.out.println("input: " + n);
        System.out.println("output: " + retVal);

    }
}
