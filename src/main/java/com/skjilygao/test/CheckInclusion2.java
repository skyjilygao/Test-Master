
package com.skjilygao.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * 参考: https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1016/
 */
class CheckInclusion2 {
    List<String> list = new ArrayList<>();
    Set<String> allSet = new HashSet<>();
    public static void main(String[] args) {
        int a = 18;
        System.out.println(a/10);
        System.out.println(a%10);
        int mod = 9 % 10;
        a = 9;
        System.out.println(a/10);
        System.out.println(a%10);

       /* CheckInclusion2 checkInclusion2 = new CheckInclusion2();
        String s1 = "prosperity";
//        checkInclusion(s1,"ccccbbbbaaaa");
//        List<String> list = sequence4(s1);
        System.out.println("----------"+checkInclusion2.checkInclusion(s1,"properties"));
        System.out.println("结束----------"+checkInclusion2.allSet);*/
       /* list = sequence2(s1);
        System.out.println(list);*/


    }

    public boolean checkInclusion(String s1, String s2) {
        list = new ArrayList<>();
        allSet = new HashSet<>();
        sequence(s1);
        allSet.forEach( as -> list.add(as));
        System.out.println("结束----------"+allSet);
        for(String str : list){
            if(s2.contains(str)){
                return true;
            }
        }
        return false;
    }

    private void sequence(String s1) {
        String[] strs = sequence1(s1);
        permutation(strs, 0, strs.length - 1);
    }

    private String[] sequence1(String s1) {
        byte[] bb = s1.getBytes();
        String[] strs = new String[s1.length()];
        for (int i = 0; i < bb.length; i++) {
            strs[i] = new String(new byte[]{bb[i]});
        }
        return strs;
    }


    public void permutation(String[] s,int from,int to) {
        if(to < 1 || s.length == 1){
            String ss = array2Str(s);
            list.add(ss);
            if(!allSet.contains(ss)){
                allSet.add(ss);
            }
            return;
        }
        if(from == to) {
            String ss = array2Str(s);
            list.add(ss);
            if(!allSet.contains(ss)){
                allSet.add(ss);
            }
        } else {
            for(int i=from; i<=to; i++) {
                swap(s,i,from);
                permutation(s, from+1, to);
                swap(s,from,i);
            }
        }
    }

    public void swap(String[] s,int i,int j) {
        String tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

    private String array2Str(String[] strArr21) {
        StringBuffer sb = new StringBuffer();
        for (String s : strArr21) {
            sb.append(s);
        }
        return sb.toString();
    }
}