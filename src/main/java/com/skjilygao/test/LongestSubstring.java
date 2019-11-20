package com.skjilygao.test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class LongestSubstring {
    public static void main(String[] args) {
        String string="abcabbb";
        System.out.println(string.substring(0,1));
//        System.out.println(lengthOfLongestSubstring(string));
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        Map<Character, Integer> mapAns = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            char c = s.charAt(j);
            if (map.containsKey(c)) {
                i = Math.max(map.get(c), i);
            }
            ans = Math.max(ans, j - i + 1);
            mapAns.put(c, ans);
            map.put(c, j + 1);
            System.out.println("j="+j+", c="+c +", i="+i+", ans="+ans + ", map="+map);
        }
        return ans;
    }
}
