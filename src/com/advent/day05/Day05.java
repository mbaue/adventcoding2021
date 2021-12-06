package com.advent.day05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://adventofcode.com/2021/day/5
 *
 * --- Day 5: Hydrothermal Venture ---
 * You come across a field of hydrothermal vents on the ocean floor!
 * These vents constantly produce large, opaque clouds, so it would be
 * best to avoid them if possible.
 *
 * They tend to form in lines; the submarine helpfully produces a list of nearby lines of vents
 * (your puzzle input) for you to review. For example:
 *
 * 0,9 -> 5,9
 * 8,0 -> 0,8
 * 9,4 -> 3,4
 * 2,2 -> 2,1
 * 7,0 -> 7,4
 * 6,4 -> 2,0
 * 0,9 -> 2,9
 * 3,4 -> 1,4
 * 0,0 -> 8,8
 * 5,5 -> 8,2
 * Each line of vents is given as a line segment in the format x1,y1 -> x2,y2 where x1,y1 are
 * the coordinates of one end the line segment and x2,y2 are the coordinates of the other end.
 * These line segments include the points at both ends. In other words:
 *
 * An entry like 1,1 -> 1,3 covers points 1,1, 1,2, and 1,3.
 * An entry like 9,7 -> 7,7 covers points 9,7, 8,7, and 7,7.
 * For now, only consider horizontal and vertical lines: lines where either x1 = x2 or y1 = y2.
 *
 * So, the horizontal and vertical lines from the above list would produce the following diagram:
 *
 * .......1..
 * ..1....1..
 * ..1....1..
 * .......1..
 * .112111211
 * ..........
 * ..........
 * ..........
 * ..........
 * 222111....
 * In this diagram, the top left corner is 0,0 and the bottom right corner is 9,9.
 * Each position is shown as the number of lines which cover that point or . if no
 * line covers that point. The top-left pair of 1s, for example, comes from 2,2 -> 2,1;
 * the very bottom row is formed by the overlapping lines 0,9 -> 5,9 and 0,9 -> 2,9.
 *
 * To avoid the most dangerous areas, you need to determine the number of points where at least
 * two lines overlap. In the above example, this is anywhere in the diagram with a 2 or larger - a total of 5 points.
 *
 * Consider only horizontal and vertical lines. At how many points do at least two lines overlap?
 *
 * Your puzzle answer was 5084.
 */

public class Day05 {

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv05.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int[][] pole = new int[1000][1000];
            int sum = 0;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] part = line.split(" -> ");
                int x1 = Integer.parseInt(part[0].substring(0, part[0].indexOf(',')));
                int y1 = Integer.parseInt(part[0].substring(part[0].indexOf(',') + 1));
                int x2 = Integer.parseInt(part[1].substring(0, part[1].indexOf(',')));
                int y2 = Integer.parseInt(part[1].substring(part[1].indexOf(',') + 1));

                if (x1 == x2 || y1 == y2) {
                    System.out.println(line);
                    if (x1 == x2) {
                        int min = Integer.min(y1, y2);
                        int max = Integer.max(y1, y2);
                        for (int i = min; i <= max; i++) {
                            pole[x1][i]++;
                        }
                    } else {
                        int min = Integer.min(x1, x2);
                        int max = Integer.max(x1, x2);
                        for (int i = min; i <= max; i++) {
                            pole[i][y1]++;
                        }
                    }
                }
            }
            for (int x = 0; x < 1000; x++) {
                for (int y = 0; y < 1000; y++) {
                    if (pole[x][y] > 1) {
                        sum++;
                    }
                }
            }
            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("sum = " + sum);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
