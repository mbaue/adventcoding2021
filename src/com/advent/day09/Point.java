package com.advent.day09;

public class Point {

    int row;
    int column;

    public Point(int row, int position) {
        this.row = row;
        this.column = position;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object obj) {
        Point x = (Point) obj;
        return (x.getRow() == this.getRow() && x.getColumn() == this.getColumn());
    }
}
