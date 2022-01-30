package com.advent.day13;

public class Dot implements Comparable {

    int x;
    int y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        Dot d = (Dot) o;
        return this.x == d.getX() && this.y == d.getY();
    }

    @Override
    public String toString() {
        return "Dot{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Dot d = (Dot) o;
        int compareX = Integer.signum(this.getX() - d.getX());
        int compareY = Integer.signum(this.getY() - d.getY());
        return (compareX == 0) ? compareY : compareX;
    }
}
