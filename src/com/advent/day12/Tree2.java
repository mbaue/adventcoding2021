package com.advent.day12;

import java.util.ArrayList;
import java.util.Set;

public class Tree2 {

    private int pathsCount;
    private Graph g;
    private String endNode;
    private TreeNode2 actualNode;

    public Tree2(String nodeStart, String nodeEnd, Graph g) {
        this.pathsCount = 0;
        this.g = g;
        this.endNode = nodeEnd;
        this.actualNode = null;
        this.addNode(nodeStart, false);
    }

    public void addNode(String nodeName, boolean usedFlag) {
        TreeNode2 tn = new TreeNode2(nodeName, actualNode);
        actualNode = tn;
        tn.setDoubleUsed(usedFlag);
        ArrayList<String> path = tn.getPath();
        if (nodeName.equals(endNode)) {
            System.out.print("      path ========== " + path);
            pathsCount++;
            System.out.println(" ===========  " + pathsCount + " =================");
        } else {
            Set<String> sousedi = g.findNode(nodeName).getNodeSuccedersString();
            for (String s : sousedi) {
                if (Character.isUpperCase(s.charAt(0))) {
                    //System.out.print("naslednik *" + nodeName + "* (level=" + (tn.getLevel()) + ")  --- ");
                    this.addNode(s, tn.isDoubleUsed());
                } else {
                    if (!path.contains(s)) {
                        //System.out.print("naslednik *" + nodeName + "* (level=" + (tn.getLevel()) + ")  --- ");
                        this.addNode(s, tn.isDoubleUsed());
                    } else {
                        if (!s.equals("start") && !tn.isDoubleUsed()) {
                            System.out.print("naslednik *" + nodeName + "* (level=" + (tn.getLevel()) + ")  --- ");
                            this.addNode(s, true);
                        }
                    }
                }
            }
        }
        actualNode = tn.getParentTreeNode();
    }

    public int getPathsCount() {
        return pathsCount;
    }
}