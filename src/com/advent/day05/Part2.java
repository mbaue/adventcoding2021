package com.advent.day05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * --- Day 5: Hydrothermal Venture ---
 * --- Part Two ---
 * Unfortunately, considering only horizontal and vertical lines
 * doesn't give you the full picture; you need to also consider diagonal lines.
 *
 * Because of the limits of the hydrothermal vent mapping system,
 * the lines in your list will only ever be horizontal, vertical,
 * or a diagonal line at exactly 45 degrees. In other words:
 *
 * An entry like 1,1 -> 3,3 covers points 1,1, 2,2, and 3,3.
 * An entry like 9,7 -> 7,9 covers points 9,7, 8,8, and 7,9.
 * Considering all lines from the above example would now produce the following diagram:
 *
 * 1.1....11.
 * .111...2..
 * ..2.1.111.
 * ...1.2.2..
 * .112313211
 * ...1.2....
 * ..1...1...
 * .1.....1..
 * 1.......1.
 * 222111....
 * You still need to determine the number of points where at least two lines overlap.
 * In the above example, this is still anywhere in the diagram with a 2 or larger - now a total of 12 points.
 *
 * Consider all of the lines. At how many points do at least two lines overlap?
 *
 * Your puzzle answer was 17882.
 */

public class Part2 {

    public static final int FIELD_SIZE = 1000;

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv05.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            int[][] pole = new int[FIELD_SIZE][FIELD_SIZE];
            int sum = 0;

            while ((line = br.readLine()) != null) {
                String[] part = line.split(" -> ");
                int x1 = Integer.parseInt(part[0].substring(0, part[0].indexOf(',')));
                int y1 = Integer.parseInt(part[0].substring(part[0].indexOf(',') + 1));
                int x2 = Integer.parseInt(part[1].substring(0, part[1].indexOf(',')));
                int y2 = Integer.parseInt(part[1].substring(part[1].indexOf(',') + 1));

                if (x1 == x2) {
                    int min = Integer.min(y1, y2);
                    int max = Integer.max(y1, y2);
                    for (int i = min; i <= max; i++) {
                        pole[x1][i]++;
                    }
                } else if (y1 == y2) {
                    int min = Integer.min(x1, x2);
                    int max = Integer.max(x1, x2);
                    for (int i = min; i <= max; i++) {
                        pole[i][y1]++;
                    }
                } else {
                    int minx = Integer.min(x1, x2);
                    int maxx = Integer.max(x1, x2);
                    int miny = Integer.min(y1, y2);
                    int maxy = Integer.max(y1, y2);
                    if ((x2 - x1) / (y2 - y1) > 0) {
                        for (int x = minx, y = miny; x <= maxx && y <= maxy; x++, y++) {
                            pole[x][y]++;
                        }
                    } else {
                        for (int x = minx, y = maxy; x <= maxx && y >=miny; x++, y--) {
                            pole [x][y]++;
                        }
                    }
                }
            }
            for (int x = 0; x < FIELD_SIZE; x++) {
                for (int y = 0; y < FIELD_SIZE; y++) {
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
