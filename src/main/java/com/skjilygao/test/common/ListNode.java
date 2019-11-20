package com.skjilygao.test.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 链表
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        List<Integer> list = new ArrayList<>();

        return super.toString();
    }

    /**
     * 通过静态内部类构建 tree
     */
    public static class Builder {
        int[] vals;

        private Builder() {
        }

        public Builder(int[] vals) {
            this.vals = vals;
        }

        public ListNode build() {
            return build(vals);
        }

        private ListNode build(int[] vals) {
            ListNode tree = null;
            if (vals != null) {
                tree = new ListNode(vals[0]);
                for (int i = 0; i < vals.length; i++) {
                    ListNode tmpNode = new ListNode(vals[i]);
                    tmpNode.next = tree;
                    tree = tmpNode;
                }
            }
            return tree;
        }
    }
}