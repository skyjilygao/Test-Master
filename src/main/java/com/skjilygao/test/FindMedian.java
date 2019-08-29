package com.skjilygao.test;

/**
 * 寻找两个有序数组的中位数
 * <br> 参考：https://mp.weixin.qq.com/s?__biz=MzI2NjA3NTc4Ng==&mid=2652081367&idx=2&sn=3bb97e4864d0a562abde967c0054061d&v=1&key=e5cd714fdda6b3aa525ccbfb23bfcaa9198e099cefad23785e404a310ceefa75727fee95976b7f21d9c45bae56b4fc31997de6ea825e1bebbccd4ddfc713ab0ba618d59087444a857eff34e3a4609ac2&ascene=1&uin=OTMyNDYxMzQw&devicetype=Windows+10&version=62060844&lang=zh_CN&pass_ticket=tAR1OHOU7WugN0c9YHZrV3q%2BAzW7%2FtdVjZv2wpHf3Q3GiiZNc3Ldd2Vw6Ec7sfvw
 * @since 20190829
 * @author skyjilygao
 *
 */
public class FindMedian {
    public static void main(String[] args) {
        FindMedian findMedianNumber = new FindMedian();
        int[] nums1={1,2,3};
        int[] nums2={1,2,4,5,6};
        // 1,2,3,5,5,6,6,8
        // 9,9,13,14,23,25
        double medianNum = findMedianNumber.findMedianSortedArrays(nums1, nums2);
        System.out.println(medianNum);
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //一个小技巧：将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }
}
