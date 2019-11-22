package com.skjilygao.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 大数相乘
 * 参考：https://leetcode-cn.com/problems/multiply-strings/
 * @since 20191122
 *
 */
class MultiplyStrings {
    public String multiply(String num1, String num2) {
        if(null == num1 || "0".equals(num2)){
            return num2;
        }
        if(null == num2 || "0".equals(num1)){
            return num1;
        }
        int len1 = num1.length();
        int len2 = num2.length();
        int strs1len = len1 > len2 ? len1 : len2;
        int strs2len = len1 + len2;
        String[][] strs = new String[strs1len][strs2len];
        
        List<Integer> list = new ArrayList<>();
        int strs1Index = 0;
        for(int i1 = len1 - 1; i1 >= 0; i1--){
            String s1 = num1.substring(i1, i1 + 1);
            int n1 = Integer.valueOf(s1);
            String[] strs2 = new String[strs2len];
            int strs2Index = strs2len - 1 - strs1Index;
            int shang = 0;
            for(int i2 = len2 - 1; i2 >= 0; i2--){
                String s2 = num2.substring(i2, i2 + 1);
                int n2 = Integer.valueOf(s2);
                int ji = n1 * n2 + shang;
                System.out.println("n1="+n1+" * n2="+n2+" + shang="+shang);
                shang = ji / 10;
                int mo = ji % 10;
                strs2[strs2Index] = ""+mo;
                strs2Index--;
            }
            // 补0
            for(int bu = strs2Index; bu >=0 ; bu--){
                // strs2[bu] = "0";
            }
            System.out.println("strs1Index="+strs1Index+",->>>> "+printArr(strs2));
            strs[strs1Index] = strs2;
            strs1Index++;
        }
        int index = 0;
        int len22 = strs2len;
        for(String[] strs2 : strs){
            System.out.println("index="+index+",->>>> "+printArr(strs2));
            index++;
        }
        for(int i = 0; i<strs1len;i++){
            
        }
        // StringBuffer sb = new StringBuffer();
        // if(shang > 0){
        //     sb.append(shang);
        // }
        // for(int i = list.size() -1; i>=0; i--){
        //     sb.append(list.get(i));
        // }
        return "";
    }
    
    private String printArr(String[] strs){
        StringBuffer sb =new StringBuffer();
        for(String s: strs){
            sb.append(" "+s);
        }
        return sb.toString();
    }
}