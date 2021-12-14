package com.advent.day09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * --- Day 9: Smoke Basin ---
 * --- Part Two ---
 * Next, you need to find the largest basins so you know what areas
 * are most important to avoid.
 *
 * A basin is all locations that eventually flow downward to a single low point.
 * Therefore, every low point has a basin, although some basins are very small.
 * Locations of height 9 do not count as being in any basin, and all other locations
 * will always be part of exactly one basin.
 *
 * The size of a basin is the number of locations within the basin,
 * including the low point. The example above has four basins.
 *
 * The top-left basin, size 3:
 *
 * 2199943210
 * 3987894921
 * 9856789892
 * 8767896789
 * 9899965678
 * The top-right basin, size 9:
 *
 * 2199943210
 * 3987894921
 * 9856789892
 * 8767896789
 * 9899965678
 * The middle basin, size 14:
 *
 * 2199943210
 * 3987894921
 * 9856789892
 * 8767896789
 * 9899965678
 * The bottom-right basin, size 9:
 *
 * 2199943210
 * 3987894921
 * 9856789892
 * 8767896789
 * 9899965678
 * Find the three largest basins and multiply their sizes together.
 * In the above example, this is 9 * 14 * 9 = 1134.
 *
 * What do you get if you multiply together the sizes of the three largest basins?
 *
 * Your puzzle answer was 1330560.
 */

public class Part2 {

    public static final int LINES = 100;
    public static final int CHARS = 100;

    public static BasinField[][] data = new BasinField[LINES][CHARS];
    public static List<Point> minPoints = new ArrayList<>();
    public static List<Integer> basinSizes = new ArrayList<>();

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv09.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            int cnt = 0;

            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    data[cnt][i] = new BasinField(Integer.parseInt(line.substring(i, i + 1)));
                }
                cnt++;
            }

            for (int i = 0; i < LINES; i++) {
                for (int j = 0; j < CHARS; j++) {
                    if (checkIfMin(i, j)) {
                        minPoints.add(new Point(i, j));
                    }
                }
            }

            for (Point p : minPoints) {
                int basinSize = countBasinSize(p.getRow(), p.getColumn());
                basinSizes.add(basinSize);
            }

            basinSizes.sort(Collections.reverseOrder());

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("product of three biggest basins = " + (basinSizes.get(0)  * basinSizes.get(1) * basinSizes.get(2)));
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean checkIfMin(int i, int j) {
        int x = (data[i][j]).getValue();
        if (i > 0) {
            if (x >= (data[i - 1][j]).getValue()) {
                return false;
            }
        }
        if (i < LINES - 1) {
            if (x >= (data[i + 1][j]).getValue()) {
                return false;
            }
        }
        if (j < CHARS - 1) {
            if (x >= (data[i][j + 1]).getValue()) {
                return false;
            }
        }
        if (j > 0) {
            if (x >= (data[i][j - 1]).getValue()) {
                return false;
            }
        }
        return true;
    }

    public static int countBasinSize(int line, int position) {
        int fieldsCnt = 0;
        if (0 <= line  && line < LINES && 0 <= position && position < CHARS && (!data[line][position].isFlag()) && data[line][position].getValue()<9) {
            fieldsCnt++;
            data[line][position].setFlag(true);
            fieldsCnt += countBasinSize(line - 1, position);
            fieldsCnt += countBasinSize(line + 1, position);
            fieldsCnt += countBasinSize(line, position + 1);
            fieldsCnt += countBasinSize(line, position - 1);
        }
        return fieldsCnt;
    }

}