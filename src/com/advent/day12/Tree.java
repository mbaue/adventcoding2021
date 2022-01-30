package com.advent.day12;

import java.util.ArrayList;
import java.util.Set;

public class Tree {

    private int pathsCount;
    private Graph g;
    private String endNode;
    private TreeNode actualNode;

    public Tree(String nodeStart, String nodeEnd, Graph g) {
        this.pathsCount = 0;
        this.g = g;
        this.endNode = nodeEnd;
        this.actualNode = null;
        this.addNode(nodeStart);
    }

    public void addNode(String nodeName) {
        TreeNode tn = new TreeNode(nodeName, actualNode);
        actualNode = tn;
        ArrayList<String> path = tn.getPath();
        if (nodeName.equals(endNode)) {
            System.out.print("      path ========== " + path);
            pathsCount++;
            System.out.println(" ===========  " + pathsCount + " =================");
        } else {
            Set<String> sousedi = g.findNode(nodeName).getNodeSuccedersString();
            for (String s : sousedi) {
                if (!path.contains(s) || (Character.isUpperCase(s.charAt(0)))
                ) {
                    System.out.print("naslednik *" + nodeName + "* (level=" + (tn.getLevel()) + ")  --- ");
                    this.addNode(s);
                } else {
                    System.out.println(" ---uzel " + s + " uz je pouzity (" + path + ")");
                }
            }
        }
        actualNode = tn.getParentTreeNode();
    }

    public int getPathsCount() {
        return pathsCount;
    }

}