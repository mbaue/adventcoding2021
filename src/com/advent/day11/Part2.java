package com.advent.day11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * --- Day 11: Dumbo Octopus ---
 * --- Part Two ---
 * It seems like the individual flashes aren't bright enough to navigate.
 * However, you might have a better option: the flashes seem to be synchronizing!
 *
 * In the example above, the first time all octopuses flash simultaneously is step 195:
 *
 * After step 193:
 * 5877777777
 * 8877777777
 * 7777777777
 * 7777777777
 * 7777777777
 * 7777777777
 * 7777777777
 * 7777777777
 * 7777777777
 * 7777777777
 *
 * After step 194:
 * 6988888888
 * 9988888888
 * 8888888888
 * 8888888888
 * 8888888888
 * 8888888888
 * 8888888888
 * 8888888888
 * 8888888888
 * 8888888888
 *
 * After step 195:
 * 0000000000
 * 0000000000
 * 0000000000
 * 0000000000
 * 0000000000
 * 0000000000
 * 0000000000
 * 0000000000
 * 0000000000
 * 0000000000
 * If you can calculate the exact moments when the octopuses will all flash simultaneously,
 * you should be able to navigate through the cavern. What is the first step during which all octopuses flash?
 *
 * Your puzzle answer was 364.
 */

public class Part2 {

    public static final int SIZE = 10;
    public static final int OCTOPUSES_COUNT = 100;
    public static int[][] poleTemp = new int[SIZE][SIZE];
    public static int[][] poleNext = new int[SIZE][SIZE];

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv11.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int[][] pole = new int[SIZE][SIZE];
            int cnt = 0;

            while ((line = br.readLine()) != null) {
                for (int i = 0; i < SIZE; i++) {
                    pole[cnt][i] = Integer.parseInt(line.substring(i, i + 1));
                }
                cnt++;
            }
            System.out.println("--- init state");
            printPole(pole);

            int flashCountCycle;
            poleTemp = Arrays.copyOf(pole, pole.length);
            int cycleCount = 0;

            do {
                cycleCount++;
                List<Point> listOfNuls = new ArrayList<>();
                flashCountCycle = 0;

                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        poleNext[i][j] = (poleTemp[i][j] + 1) % 10;
                        if (poleNext[i][j] == 0) {
                            flashCountCycle++;
                            listOfNuls.add(new Point(i, j));
                        }
                    }
                }

                for (Point p : listOfNuls) {
                    flashCountCycle += incrementNeighbors(p.getX(), p.getY());
                }

                System.out.println("--- after " + cycleCount + ". round");
                printPole(poleNext);
                poleTemp = Arrays.copyOf(poleNext, poleNext.length);
            } while (!(flashCountCycle == OCTOPUSES_COUNT));

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("flash count = " + cycleCount);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printPole(int[][] arr) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        System.out.println("------------");
    }

    public static int incrementNeighbors(int i, int j) {
        int flash = 0;
        for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
                if (0 <= k && k < SIZE && 0 <= l && l < SIZE && !(i == k && j == l) && poleNext[k][l] != 0) {
                    poleNext[k][l] = (poleNext[k][l] + 1) % 10;
                    if (poleNext[k][l] == 0) {
                        flash++;
                        flash += incrementNeighbors(k, l);
                    }
                }
            }
        }
        return flash;
    }
}
