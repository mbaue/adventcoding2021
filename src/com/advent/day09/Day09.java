package com.advent.day09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://adventofcode.com/2021/day/9
 *
 * --- Day 9: Smoke Basin ---
 * These caves seem to be lava tubes. Parts are even still volcanically active;
 * small hydrothermal vents release smoke into the caves that slowly settles like rain.
 *
 * If you can model how the smoke flows through the caves, you might be able
 * to avoid it and be that much safer. The submarine generates a heightmap
 * of the floor of the nearby caves for you (your puzzle input).
 *
 * Smoke flows to the lowest point of the area it's in. For example,
 * consider the following heightmap:
 *
 * 2199943210
 * 3987894921
 * 9856789892
 * 8767896789
 * 9899965678
 * Each number corresponds to the height of a particular location,
 * where 9 is the highest and 0 is the lowest a location can be.
 *
 * Your first goal is to find the low points - the locations that are lower
 * than any of its adjacent locations. Most locations have four adjacent
 * locations (up, down, left, and right); locations on the edge or corner
 * of the map have three or two adjacent locations, respectively.
 * (Diagonal locations do not count as adjacent.)
 *
 * In the above example, there are four low points, all highlighted:
 * two are in the first row (a 1 and a 0), one is in the third row (a 5),
 * and one is in the bottom row (also a 5). All other locations on the heightmap
 * have some lower adjacent location, and so are not low points.
 *
 * The risk level of a low point is 1 plus its height. In the above example,
 * the risk levels of the low points are 2, 1, 6, and 6. The sum of the risk
 * levels of all low points in the heightmap is therefore 15.
 *
 * Find all of the low points on your heightmap. What is the sum
 * of the risk levels of all low points on your heightmap?
 *
 * Your puzzle answer was 502.
 */

public class Day09 {

    public static final int LINES = 100;
    public static final int CHARS = 100;
    public static String[] data = new String[LINES];

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv09.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            int cnt = 0;
            int minCnt = 0;

            while ((line = br.readLine()) != null) {
                data[cnt] = line;
                cnt++;
            }

            for (int i = 0; i < LINES; i++) {
                for (int j = 0; j < CHARS; j++) {
                    if (checkIfMin(i, j)) {
                        minCnt = minCnt + 1 + Integer.parseInt(data[i].substring(j, j + 1));
                    }
                }
            }
            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("sum of min = " + minCnt);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean checkIfMin(int i, int j) {
        int x = Integer.parseInt(data[i].substring(j, j + 1));
        if (i > 0) {
            if (x >= Integer.parseInt(data[i - 1].substring(j, j + 1))) {
                return false;
            }
        }
        if (i < LINES - 1) {
            if (x >= Integer.parseInt(data[i + 1].substring(j, j + 1))) {
                return false;
            }
        }
        if (j < CHARS - 1) {
            if (x >= Integer.parseInt(data[i].substring(j + 1, j + 2))) {
                return false;
            }
        }
        if (j > 0) {
            if (x >= Integer.parseInt(data[i].substring(j - 1, j))) {
                return false;
            }
        }
        return true;
    }
}
