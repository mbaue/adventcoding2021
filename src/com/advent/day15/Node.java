package com.advent.day15;

import java.util.*;

public class Node implements Comparable<Node> {

    private int dist;
    private Node predecessor;
    private int x;
    private int y;
    //private int val;

    public Node(Node predecessor, int x, int y) {
        this.dist = Integer.MAX_VALUE;
        this.predecessor = predecessor;
        this.x = x;
        this.y = y;
    }

    public List<Node> getNeighborVertices() {
        List<Node> set = new ArrayList<>();
        int x = this.x;
        int y = this.y;
        if (x - 1 >= 0) {
            Node n = new Node(this, x - 1, y);
            set.add(n);
        }
        if (x + 1 < Day15.GRAPH_DIMENSION) {
            Node n = new Node(this, x + 1, y);
            set.add(n);
        }
        if (y - 1 >= 0) {
            Node n = new Node(this, x, y - 1);
            set.add(n);
        }
        if (y + 1 < Day15.GRAPH_DIMENSION) {
            Node n = new Node(this, x, y + 1);
            set.add(n);
        }
        return set;
    }

    public boolean setDist(int dist) {
        if (dist < this.getDist()) {
            this.dist = dist;
            return true;
        }
        return false;
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDist() {
        return dist;
    }

    @Override
    public int compareTo(Node o) {
        return this.dist - o.dist;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node n) {
            return this.x == n.x && this.y == n.y;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", dist=" + dist +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
