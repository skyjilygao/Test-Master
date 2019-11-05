package com.skjilygao.test.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 树结构
 *
 * @author skyjilygao
 * @date 20191104
 * @since 20191104
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    private TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    /**
     * 通过静态内部类构建 tree
     */
    public static class Builder {
        Integer[] vals;

        private Builder() {
        }

        public Builder(Integer[] vals) {
            this.vals = vals;
        }

        public TreeNode build() {
            return build(vals, 0);
        }

        private TreeNode build(Integer[] vals, int index) {
            TreeNode tree = null;
            if (index < vals.length) {
                Integer val = vals[index];
                if (val == null) {
                    return null;
                }
                tree = new TreeNode(val);
                tree.left = build(vals, 2 * index + 1);
                tree.right = build(vals, 2 * index + 2);
            }
            return tree;
        }
    }

    public static class Operation {
        /**
         * 打印树结构
         *
         * @param root
         */
        public static void printTree(TreeNode root) {
            if (root == null) {
                throw new NullPointerException("the tree is empty.");
            }
            // 得到树的深度
            int treeDepth = getTreeDepth(root);

            // 最后一行的宽度为2的（n - 1）次方乘3，再加1
            // 作为整个二维数组的宽度
            int arrayHeight = treeDepth * 2 - 1;
            int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
            // 用一个字符串数组来存储每个位置应显示的元素
            String[][] res = new String[arrayHeight][arrayWidth];
            // 对数组进行初始化，默认为一个空格
            for (int i = 0; i < arrayHeight; i++) {
                for (int j = 0; j < arrayWidth; j++) {
                    res[i][j] = " ";
                }
            }

            // 从根节点开始，递归处理整个树
            // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
            writeArray(root, 0, arrayWidth / 2, res, treeDepth);

            // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
            System.out.println("树：");
            for (String[] line : res) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < line.length; i++) {
                    sb.append(line[i]);
                    if (line[i].length() > 1 && i <= line.length - 1) {
                        i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                    }
                }
                System.out.println(sb.toString());
            }
        }

        /**
         * 用于获得树的层数
         *
         * @param root
         * @return
         */
        private static int getTreeDepth(TreeNode root) {
            return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
        }

        /**
         * 将tree 信息放到二维数组
         *
         * @param currNode
         * @param rowIndex
         * @param columnIndex
         * @param res
         * @param treeDepth
         */
        private static void writeArray(TreeNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
            // 保证输入的树不为空
            if (currNode == null) return;
            // 先将当前节点保存到二维数组中
            res[rowIndex][columnIndex] = String.valueOf(currNode.val);

            // 计算当前位于树的第几层
            int currLevel = ((rowIndex + 1) / 2);
            // 若到了最后一层，则返回
            if (currLevel == treeDepth) return;
            // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
            int gap = treeDepth - currLevel - 1;

            // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
            if (currNode.left != null) {
                res[rowIndex + 1][columnIndex - gap] = "/";
                writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
            }

            // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
            if (currNode.right != null) {
                res[rowIndex + 1][columnIndex + gap] = "\\";
                writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
            }
        }

        /**
         * 中序遍历：左-根-右
         *
         * @return
         */
        public static List<Integer> inOrderTraversal(TreeNode root) {
            List<Integer> traversalResult = new ArrayList();
            inOrderTraversal(root, traversalResult);
            return traversalResult;
        }

        /**
         * 中序遍历: 左-根-右
         *
         * @param root            TreeNode
         * @param traversalResult List
         */
        private static void inOrderTraversal(TreeNode root, List<Integer> traversalResult) {
            if (root != null) {
                if (root.left != null) {
                    inOrderTraversal(root.left, traversalResult);
                }
                traversalResult.add(root.val);
                if (root.right != null) {
                    inOrderTraversal(root.right, traversalResult);
                }
            }
        }

        /**
         * 先序遍历：根-左-右
         *
         * @return
         */
        public static List<Integer> preOrderTraversal(TreeNode root) {
            List<Integer> traversalResult = new ArrayList();
            preOrderTraversal(root, traversalResult);
            return traversalResult;
        }

        /**
         * 先序遍历：根-左-右
         *
         * @param root
         * @param traversalResult
         */
        private static void preOrderTraversal(TreeNode root, List<Integer> traversalResult) {
            if (root != null) {
                traversalResult.add(root.val);
                if (root.left != null) {
                    preOrderTraversal(root.left, traversalResult);
                }
                if (root.right != null) {
                    preOrderTraversal(root.right, traversalResult);
                }
            }
        }

        public static List<Integer> postOrderTraversal(TreeNode root) {
            List<Integer> traversalResult = new ArrayList();
            postOrderTraversal(root, traversalResult);
            return traversalResult;
        }

        /**
         * 后序遍历：右-根-左
         *
         * @param root
         * @param traversalResult
         */
        private static void postOrderTraversal(TreeNode root, List<Integer> traversalResult) {
            if (root != null) {
                TreeNode right = root.right;
                TreeNode left = root.left;
                if (notNull(right)) {
                    postOrderTraversal(right, traversalResult);
                }
                traversalResult.add(root.val);
                if (notNull(left)) {
                    postOrderTraversal(left, traversalResult);
                }
            }
        }

        /**
         * @param tree
         * @return
         */
        public static boolean notNull(TreeNode tree) {
            return tree == null ? false : true;
        }
    }
}
