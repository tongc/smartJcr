package com.goda5.smartjcr.tree;

public class TreeTest {
    public static void main(String[] args) {
        /**
         *                root
         *          1              2     3    456
         *     11       12       21 22 31 32
         * 111 112 113
         */
        TreeNode root = new TreeNode("root");
        TreeNode t1 = new TreeNode("1");
        TreeNode t2 = new TreeNode("2");
        TreeNode t3 = new TreeNode("3");
        TreeNode t4 = new TreeNode("4");
        TreeNode t5 = new TreeNode("5");
        TreeNode t6 = new TreeNode("6");
        TreeNode t11 = new TreeNode("11");
        TreeNode t12 = new TreeNode("12");
        TreeNode t21 = new TreeNode("21");
        TreeNode t22 = new TreeNode("22");
        TreeNode t31 = new TreeNode("31");
        TreeNode t32 = new TreeNode("32");
        TreeNode t111 = new TreeNode("111");
        TreeNode t112 = new TreeNode("112");
        TreeNode t113 = new TreeNode("113");
        root.children.add(t1);
        root.children.add(t2);
        root.children.add(t3);
        root.children.add(t4);
        root.children.add(t5);
        root.children.add(t6);

        t1.children.add(t11);
        t1.children.add(t12);

        t2.children.add(t21);
        t2.children.add(t22);

        t3.children.add(t31);
        t3.children.add(t32);

        t11.children.add(t111);
        t11.children.add(t112);
        t11.children.add(t113);

        root.breadth();
        System.out.println("-----------------");
        root.breadthRec(root);
        System.out.println("-----------------");
        root.depth(root);
    }
}
