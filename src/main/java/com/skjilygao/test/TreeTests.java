package com.skjilygao.test;

import com.skjilygao.test.common.TreeNode;

/**
 * 操作树
 * @author skyjilygao
 * @since 20191104
 */
public class TreeTests {

    public TreeNode createTree(Integer[] vals, int index){
        TreeNode tree = null;

        if(index < vals.length){
            Integer val = vals[index];
            if(val == null){
                return null;
            }
            tree = new TreeNode(val);
            tree.left = createTree(vals, 2*index + 1);
            tree.right = createTree(vals, 2*index + 2);
        }
        return tree;
    }

    public static void main(String[] args) {
        TreeTests treeTests = new TreeTests();
        Integer[] arr = new Integer[]{3,9,20,null,null,15,7};
        treeTests.createTree(arr, 0);
    }
}
