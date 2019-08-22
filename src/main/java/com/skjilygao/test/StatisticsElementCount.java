package com.skjilygao.test;

import java.util.HashMap;
import java.util.Map;

/**
 * 统计结合中各个元素出现次数
 * Created by SKYJILYGAO on 2017/6/9.
 * @author skyjilygao
 * @since 20170609
 */
public class StatisticsElementCount {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 77, 4, 7, 2, 34, 2, 88, 9, 4, 77, 4, 3};
        int size = array.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(size);
        int count;
        for (int i = 0; i < size; i++) {
            int e = array[i];
            if (!map.containsKey(e)) {
                count = 0;
            } else {
                count = map.get(e);
            }
            map.put(e, ++count);
        }
        System.out.println(map);
    }
}