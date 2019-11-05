package com.skjilygao.test;

import com.skjilygao.test.common.TreeNode;

/**
 * 操作树
 *
 * @author skyjilygao
 * @since 20191104
 */
public class TreeTests {

    public static void main(String[] args) {
        // 根据给定的数组创建一棵树
        Integer[] arr = new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1};
        TreeNode tree = new TreeNode.Builder(arr).build();
        // 将刚刚创建的树打印出来
        TreeNode.Operation.printTree(tree);
        System.out.print("\n 先序遍历：");
        TreeNode.Operation.preOrderTraversal(tree).forEach(n -> System.out.print(" " + n));
        System.out.print("\n 中序遍历：");
        TreeNode.Operation.inOrderTraversal(tree).forEach(n -> System.out.print(" " + n));
        System.out.print("\n 后序遍历：");
        TreeNode.Operation.postOrderTraversal(tree).forEach(n -> System.out.print(" " + n));
    }
}
