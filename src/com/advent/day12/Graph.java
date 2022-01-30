package com.advent.day12;

import java.util.ArrayList;

public class Graph {

    private ArrayList<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node n) {
        if (!nodes.contains(n)) {
            nodes.add(n);
        }
    }

    public void addEdge(Node n1, Node n2) {
        if (nodes.contains(n1)) {
            nodes.get(nodes.indexOf(n1)).addSucceder(n2);
        } else {
            System.out.println("uzel " + n1.toString() + " tam chybi, coz je ERROR");
        }
        if (nodes.contains(n2)) {
            nodes.get(nodes.indexOf(n2)).addSucceder(n1);
        } else {
            System.out.println("uzel " + n2.toString() + " tam chybi, coz je ERROR");
        }
    }

    public Node findNode(String s) {
        for (Node n : nodes) {
            if (n.getNodeName().equals(s)) {
                return n;
            }
        }
        return null;
    }

    public int findPathsFrom(String nodeStart, String nodeEnd) {
        Tree t = new Tree(nodeStart, nodeEnd, this);
        return t.getPathsCount();
    }

    public void print() {
        for (Node n : nodes) {
            System.out.println(n);
        }
    }
}
