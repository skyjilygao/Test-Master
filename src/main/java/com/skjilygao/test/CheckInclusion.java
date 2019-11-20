
package com.skjilygao.test;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * 参考: https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1016/
 */
class CheckInclusion {
    public static void main(String[] args) {
//        "intention"
//        "execution"
        String s1 = "intention";
        List<String> list = sequence4(s1);
        System.out.println("结束----------"+list);
       /* list = sequence2(s1);
        System.out.println(list);*/


    }
    public static boolean checkInclusion(String s1, String s2) {
        List<String> list = sequence(s1);
        for(String str : list){
            if(s2.contains(str)){
                return true;
            }
        }
        return false;
    }

    /**
     * 双重循环得到排列
     * @param str
     * @return
     */
    public static List<String> sequence(String str){
        long d1 = System.nanoTime();
        byte[] bb = str.getBytes();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < bb.length; i++) {
            String preffix = new String(new byte[]{bb[i]});
            StringBuffer seq = new StringBuffer(preffix);
            for (int j = 0; j < bb.length; j++) {
                String s = new String(new byte[]{bb[j]});
                if(i!=j){
                    seq.append(s);
                }
            }
            list.add(seq.toString());
        }
        System.out.println("sequence 耗时：" + (System.nanoTime() - d1) + " ns");
        return list;
    }

    /**
     *
     * @param str
     * @return
     */
    public static List<String> sequence2(String str){
        long d1 = System.nanoTime();
        byte[] bb = str.getBytes();
        List<String> list = new ArrayList<>();
        list.add(str);
        for (int i = 1; i < bb.length; i++) {
            String preffix = new String(new byte[]{bb[i]});
            StringBuffer seq = new StringBuffer(preffix);
            String sub1 = str.substring(0,i);
            String sub2 = str.substring(i + 1,str.length());
            seq.append(sub1).append(sub2);
            list.add(seq.toString());
        }
        System.out.println("sequence2 耗时：" + (System.nanoTime() - d1) + " ns");
        return list;
    }

    public static List<String> sequence3(String str){
        long d1 = System.nanoTime();
        byte[] bb = str.getBytes();
        int len = str.length();
        List<String> list3 = new ArrayList<>();
        if(len == 1){
            list3.add(str);
            return list3;
        }
        String[] strArr = new String[len];
        String[] strArr2 = new String[len];
        Set<String> set = new HashSet<>();
        for (int i = 0; i < bb.length; i++) {
            String s = new String(new byte[]{bb[i]});
            strArr[i] = s;
            strArr2[i] = s;
        }
        for (int i = 0; i < strArr.length; i++) {
            String s = strArr[i];
            System.out.println("-----------------------------------s="+s);
            for (int j = 0; j < strArr.length; j++) {

                String[] strArr21 = arrayCopy(strArr2);
                String ss = strArr[j];
                System.out.println(s+"替换成="+ss);
                strArr21[i] = ss;
                strArr21[j] = s;
                String rStr = array2Str(strArr21);
                if(!set.contains(rStr)){
                    set.add(rStr);
                }
                System.out.println("替换前"+str+",替换后="+rStr);
            }
        }
        set.forEach(ss -> list3.add(ss));
        return list3;
    }

    public static List<String> sequence5(String str){
        long d1 = System.nanoTime();
        byte[] bb = str.getBytes();
        int len = str.length();
        List<String> list3 = new ArrayList<>();
        if(len == 1){
            list3.add(str);
            return list3;
        }else if (len == 2){
            String s1 = new String(new byte[]{bb[0]});
            String s2 = new String(new byte[]{bb[1]});
            list3.add(s1 + s2);
            list3.add(s2 + s1);
        }else{
            String[] strArr = new String[len];
            String[] strArr2 = new String[len];
            Set<String> set = new HashSet<>();
            for (int i = 0; i < bb.length; i++) {
                String s = new String(new byte[]{bb[i]});
                strArr[i] = s;
                strArr2[i] = s;
            }
            for (int i = 0; i < strArr.length; i++) {
                String s = strArr[i];
                System.out.println("-----------------------------------s="+s);
                for (int j = 0; j < strArr.length; j++) {

                    String[] strArr21 = arrayCopy(strArr2);
                    String ss = strArr[j];
                    System.out.println(s+"替换成="+ss);
                    strArr21[i] = ss;
                    strArr21[j] = s;
                    String rStr = array2Str(strArr21);
                    if(!set.contains(rStr)){
                        set.add(rStr);
                    }
                    System.out.println("替换前"+str+",替换后="+rStr);
                }
            }
            set.forEach(ss -> list3.add(ss));
        }
        return list3;
    }

    public static List<String> sequence4(String str){
        long d1 = System.nanoTime();
        int len = str.length();
        List<String> list3 = new ArrayList<>();
//        Set<String> set = new HashSet<>();
        if(len == 1){
            list3.add(str);
            return list3;
        }
        byte[] bb = str.getBytes();
        String[] strArr = new String[len];
        String[] strArr2 = new String[len];
        String[] strArr3 = new String[len];
        Set<String> set = new HashSet<>();
        for (int i = 0; i < bb.length; i++) {
            String s = new String(new byte[]{bb[i]});
            strArr[i] = s;
            strArr2[i] = s;
        }
        for (int i = 0; i < strArr.length; i++) {
            String s = strArr[i];
            System.out.println("-----------------------------------s="+s);
            int strArr21Len = strArr.length - 1;
            int ii = 0;
            String[] strArr21 = new String[strArr21Len];
            for (int j = 0; j < strArr.length; j++) {
                if(i==j){
                    continue;
                }
                String ss = strArr[j];
                strArr21[ii] = ss;
                ii++;
            }
            String rStr = array2Str(strArr21);
            if(rStr.length() >2){
                List<String> list31 = sequence4(rStr);
                list31.forEach(list311 -> {
                    String sss = s+list311;
                    if(!set.contains(sss)){
                        set.add(sss);
                    }
                });
            }else{
                List<String> list31 = sequence5(rStr);
                list31.forEach(list311 -> {
//                    list3.add(s+list311);
                    String sss = s+list311;
                    if(!set.contains(sss)){
                        set.add(sss);
                    }
                });
            }
            System.out.println("前缀="+s+", 组合排列="+list3);
        }
        set.forEach(st -> list3.add(st));
        return list3;
    }

    private static String array2Str(String[] strArr21) {
        StringBuffer sb = new StringBuffer();
        for (String s : strArr21) {
            sb.append(s);
        }
        return sb.toString();
    }

    private static String[] arrayCopy(String[] strArr) {
        String[] arr = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            arr[i] = strArr[i];
        }
        return arr;
    }

    public static List<String> listCopy(List<String> list){
        List<String> list3 = new ArrayList<>();
        for (String s : list) {
            list3.add(s);
        }
        return list3;
    }
    public static String list2Str(List<String> list){
        StringBuffer sb = new StringBuffer();
        list.forEach(l -> {
            sb.append(l);
        });
        return sb.toString();
    }
}