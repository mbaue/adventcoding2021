package com.advent.day12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Node {

    private final String nodeName;
    private ArrayList<Node> nodeSucceders;

    public Node(String name) {
        this.nodeName = name;
        this.nodeSucceders = new ArrayList<>();
    }

    public String getNodeName() {
        return nodeName;
    }

    public Set<String> getNodeSuccedersString() {
        Set<String> al = new HashSet<>();
        for (Node n : nodeSucceders) {
            al.add(n.getNodeName());
        }
        return al;
    }

    public void addSucceder(Node n) {
        if (!nodeSucceders.contains(n)) {
            this.nodeSucceders.add(n);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node n) {
            return this.nodeName.equals(n.nodeName);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node n : nodeSucceders) {
            sb.append(n.nodeName).append(",");
        }
        return "Node{" +
                "name='" + nodeName + '\'' +
                ",succ=" + sb +
                '}';
    }

}