package com.advent.day12;

import java.util.ArrayList;

public class TreeNode2 {

    private String nodeName;
    private TreeNode2 parentTreeNode;
    private ArrayList<String> path;
    private int level;
    private boolean doubleUsed;

    public TreeNode2(String name, TreeNode2 parent) {
        this.nodeName = name;
        this.parentTreeNode = parent;
        if (parentTreeNode == null) {
            this.path = new ArrayList<>();
            this.level = 0;
            doubleUsed = false;
        } else {
            this.path = new ArrayList<>(parentTreeNode.getPath());
            this.level = parentTreeNode.level + 1;
            doubleUsed = parentTreeNode.doubleUsed;
        }
        this.path.add(this.nodeName);
        //System.out.println("novy uzel: *" + name + "* (level=" + this.level + ")");
    }

    public String getNodeName() {
        return nodeName;
    }

    public ArrayList<String> getPath() {
        return  path;
    }

    public TreeNode2 getParentTreeNode() {
        return parentTreeNode;
    }

    public int getLevel() {
        return level;
    }

    public boolean isDoubleUsed() {
        return doubleUsed;
    }

    public void setDoubleUsed(boolean doubleUsed) {
        this.doubleUsed = doubleUsed;
    }
}