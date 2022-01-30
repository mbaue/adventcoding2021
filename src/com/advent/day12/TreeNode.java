package com.advent.day12;

import java.util.ArrayList;

public class TreeNode {

    private String nodeName;
    private TreeNode parentTreeNode;
    private ArrayList<String> path;
    private int level;

    public TreeNode(String name, TreeNode parent) {
        this.nodeName = name;
        this.parentTreeNode = parent;
        if (parentTreeNode == null) {
            this.path = new ArrayList<>();
            this.level = 0;
        } else {
            this.path = new ArrayList<>(parentTreeNode.getPath());
            this.level = parentTreeNode.level + 1;
        }
        this.path.add(this.nodeName);
        System.out.println("novy uzel: *" + name + "* (level=" + this.level + ")");
    }

    public String getNodeName() {
        return nodeName;
    }

    public ArrayList<String> getPath() {
        return  path;
    }

    public TreeNode getParentTreeNode() {
        return parentTreeNode;
    }

    public int getLevel() {
        return level;
    }
}