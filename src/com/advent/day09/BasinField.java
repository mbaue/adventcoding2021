package com.advent.day09;

public class BasinField {

    private int value;
    private boolean flag;

    public BasinField(int value) {
        this.value = value;
        this.flag = false;
    }

    public int getValue() {
        return value;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
