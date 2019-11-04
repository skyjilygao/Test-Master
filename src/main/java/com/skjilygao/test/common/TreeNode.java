package com.skjilygao.test.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 树结构
 * @author skyjilygao
 * @since 20191104
 * @date 20191104
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    private TreeNode(){}
    public TreeNode(int val){
        this.val = val;
    }

    /**
     * 中序遍历
     * @return
     */
    public List<Integer> inOrderTraversal(){
        List<Integer> res = new ArrayList();
        inOrderTraversal(this, res);
        return res;
    }

    /**
     * 中序遍历: 递归
     * @param root TreeNode
     * @param res List
     */
    private void inOrderTraversal(TreeNode root, List < Integer > res) {
        if (root != null) {
            if (root.left != null) {
                inOrderTraversal(root.left, res);
            }
            res.add(root.val);
            if (root.right != null) {
                inOrderTraversal(root.right, res);
            }
        }
    }
}
