package com.goda5.smartjcr.tree;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Consumer;

public class TreeNode {
    public List<TreeNode> children = new ArrayList();
    public Queue<TreeNode> queue = new ArrayBlockingQueue<>(100);
    public Stack<TreeNode> stack = new Stack<>();
    public String val;

    public TreeNode(String val) {
        this.val = val;
    }

    public void breadth() {
        queue.add(this);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            System.out.println(poll.val);
            poll.children.forEach(treeNode -> queue.add(treeNode));
        }
    }

    public void breadthRec(TreeNode node) {
        if (node != null) {
            System.out.println(node.val);
            node.children.forEach(treeNode -> queue.add(treeNode));
            breadthRec(queue.poll());
        }
    }

    public void depth(TreeNode node) {
        if (node != null) {
            System.out.println(node.val);
            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.add(node.children.get(i));
            }
            if (stack.size() != 0) {
                depth(stack.pop());
            }
        }
    }

    int result = 0;
    public void findDepth(TreeNode node, int sum) {
        if(node.children == null || node.children.size() == 0) {
            if(sum > result) {
                result = sum;
            }
        }
        node.children.forEach(treeNode -> findDepth(treeNode, sum+1));
    }
}
