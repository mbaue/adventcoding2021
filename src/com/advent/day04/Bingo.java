package com.advent.day04;

public class Bingo {
    private final int BINGO_SIZE = 5;
    private int[][] bingoDesk;

    public Bingo() {
        this.bingoDesk = new int[BINGO_SIZE][BINGO_SIZE];
    }

    public void fillLine(String line, int lineNum) {
        String[] s = line.trim().split(" +");
        for (int i = 0; i < BINGO_SIZE; i++) {
            bingoDesk[lineNum][i] = Integer.parseInt(s[i]);
        }
    }

    public boolean markNumber(int drawnNumber) {
        boolean isBingo = false;
        for (int i = 0;i<BINGO_SIZE; i++) {
            for (int j = 0; j<BINGO_SIZE; j++) {
                if (this.bingoDesk[i][j] == drawnNumber) {
                    this.bingoDesk[i][j] = -1;
                    isBingo = this.checkBingo(i, j);
                    break;
                }
            }
        }
        //this.printBingo();
        return isBingo;
    }

    private boolean checkBingo(int row, int col) {
        boolean bingoRow = true;
        boolean bingoCol = true;
        for (int i = 0; i < BINGO_SIZE; i++) {
            if (this.bingoDesk[row][i] >= 0) {
                bingoRow = false;
                break;
            }
        }
        for (int i = 0; i < BINGO_SIZE; i++) {
            if (this.bingoDesk[i][col] >= 0) {
                bingoCol = false;
                break;
            }
        }
        return (bingoRow || bingoCol);
    }

    public int countSum() {
        int sum = 0;
        for (int i = 0; i < BINGO_SIZE; i++) {
            for (int j = 0; j < BINGO_SIZE; j++) {
                if (this.bingoDesk[i][j] >= 0) {
                    sum += this.bingoDesk[i][j];
                }
            }

        }
        return sum;
    }

    public void printBingo() {
        System.out.println("---------------------");
        for (int i = 0; i < BINGO_SIZE; i++) {
            for (int j = 0; j < BINGO_SIZE; j++) {
                if (this.bingoDesk[i][j] >= 0) {
                    System.out.print(this.bingoDesk[i][j]);
                    System.out.print(" ");
                } else {
                    System.out.print("x ");
                }
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }

}
