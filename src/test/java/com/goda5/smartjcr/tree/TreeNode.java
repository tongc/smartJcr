package com.goda5.smartjcr.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class TreeNode {
    public List<TreeNode> children = new ArrayList();
    public Queue<TreeNode> queue = new ArrayBlockingQueue<>(100);
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
}
