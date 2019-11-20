package com.skjilygao.test;

import com.skjilygao.test.common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <br> 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <br> 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <br> 示例：
 * <br> 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * <br> 输出：7 -> 0 -> 8
 * <br> 原因：342 + 465 = 807
 *
 * <br> 参考：https://leetcode-cn.com/explore/interview/card/bytedance/244/linked-list-and-tree/1022/
 * @since 20191108
 * @author skyjilygao
 * <br> Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class AddTwoList {
    public static void main(String[] args) {
        int[] arr1 = new int[]{};
        int[] arr2 = new int[]{};
        ListNode l1 = new ListNode.Builder(arr1).build();
        ListNode l2 = new ListNode.Builder(arr1).build();
        AddTwoList addTwoList = new AddTwoList();
        ListNode l3 = addTwoList.addTwoNumbers(l1, l2);
        System.out.println(l3.toString());
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> l1List = node2Str(l1);
        List<Integer> l2List = node2Str(l2);
        System.out.println("l1List=" + l1List);
        System.out.println("l2List=" + l2List);
        int l1Size = l1List.size();
        int l2Size = l2List.size();
        List<Integer> list;
        if (l1Size > l2Size) {
            list = sumList(l1List, l2List);
        } else {
            list = sumList(l2List, l1List);
        }
        ListNode rNode = new ListNode(list.get(list.size() - 1));

        for (int i = list.size() - 1 - 1; i >= 0; i--) {
            ListNode tempNode = new ListNode(list.get(i));
            tempNode.next = rNode;
            rNode = tempNode;
        }
        return rNode;
    }

    private List<Integer> sumList(List<Integer> list1, List<Integer> list2) {
        System.out.println("逆向list1---------=" + list2Str(list1));
        System.out.println("逆向list2---------=" + list2Str(list2));

        int l1Size = list1.size();
        int l2Size = list2.size();
        List<Integer> list = new ArrayList<>();
        boolean ok = false;
        // l1Size > l2Size
        int tmpshang = 0;
        for (int i = 0; i < l1Size; ) {
            if (!ok) {
                for (int j = 0; j < l2Size; j++) {
                    int tmpSum = list1.get(i) + list2.get(j);
                    tmpSum += tmpshang;
                    int shang = tmpSum / 10;
                    int mod = tmpSum % 10;
                    System.out.println("tmpSum=" + tmpSum + ", shang=" + shang + ", mod=" + mod);
                    list.add(mod);
                    tmpshang = shang;
                    i++;
                }
            }

            ok = true;
            if (i < l1Size) {

                System.out.println("还有多余的数据，i=" + i + ", 值=" + list1.get(i));
                int tmpSum = list1.get(i);
                tmpSum += tmpshang;
                int shang = tmpSum / 10;
                int mod = tmpSum % 10;
                System.out.println("tmpSum=" + tmpSum + ", shang=" + shang + ", mod=" + mod);
                list.add(mod);
                tmpshang = shang;

                // list.add(list1.get(i));   
                i++;
            }
        }
        if (tmpshang > 0) {
            System.out.println("遍历完成，但还有需要进位=" + tmpshang);
            list.add(tmpshang);
        }
        System.out.println("预期答案输出顺序，list=" + list);

        System.out.println("======================" + list2Str(list1) + " + " + list2Str(list2) + " = " + list2Str(list));
        return list;
    }

    private List<Integer> node2Str(ListNode node) {
        List<Integer> list = new ArrayList<>();
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        return list;
    }

    private String list2Str(List<Integer> list) {
        System.out.println("");
        System.out.println("");
        String str = "";
        for (int i = list.size() - 1; i >= 0; i--) {
            str += list.get(i);
        }
        System.out.println("逆向list" + list + ",转正向输出=" + str);
        return str;
    }
}