package com.skjilygao.test;

/**
 * 最长公共前缀
 * 参考: https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1012/
 */
class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = new String[]{"aa","a"};
        System.out.println(longestCommonPrefix(strs));
    }
    public static String longestCommonPrefix(String[] strs) {
        boolean hasCommon = true;
        String comStr = "";
        String lastComStr = "";
        int preffixLen = 1;
        if(strs == null || strs.length == 0){
            return "";
        }
        int firstStrLen = strs[0].length();
        if(preffixLen > firstStrLen){
            return "";
        }

        for(;;){
            String commonStr = strs[0].substring(0,preffixLen);
            comStr = commonStr;
            for(String str1 : strs){
                int otherStrLen = str1.length();
                if(preffixLen > otherStrLen){
                    hasCommon = false;
                    break;
                }
                String commonStr1 = str1.substring(0,preffixLen);
                if(!commonStr.equals(commonStr1)){
                    hasCommon = false;
                }
            }
            if(hasCommon){
                preffixLen++;
                if(preffixLen > firstStrLen){
                    break;
                }
            }else{
                break;
            }
        }
        return hasCommon ? comStr : strs[0].substring(0,(preffixLen-1));
    }
}