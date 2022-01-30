package com.advent.day13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * https://adventofcode.com/2021/day/13
 *
 * --- Day 13: Transparent Origami ---
 * You reach another volcanically active part of the cave. It would be nice
 * if you could do some kind of thermal imaging so you could tell ahead of
 * time which caves are too hot to safely enter.
 *
 * Fortunately, the submarine seems to be equipped with a thermal camera!
 * When you activate it, you are greeted with:
 *
 * Congratulations on your purchase! To activate this infrared thermal imaging
 * camera system, please enter the code found on page 1 of the manual.
 * Apparently, the Elves have never used this feature. To your surprise,
 * you manage to find the manual; as you go to open it, page 1 falls out.
 * It's a large sheet of transparent paper! The transparent paper is marked
 * with random dots and includes instructions on how to fold it up (your puzzle input). For example:
 *
 * 6,10
 * 0,14
 * 9,10
 * 0,3
 * 10,4
 * 4,11
 * 6,0
 * 6,12
 * 4,1
 * 0,13
 * 10,12
 * 3,4
 * 3,0
 * 8,4
 * 1,10
 * 2,14
 * 8,10
 * 9,0
 *
 * fold along y=7
 * fold along x=5
 * The first section is a list of dots on the transparent paper. 0,0 represents
 * the top-left coordinate. The first value, x, increases to the right.
 * The second value, y, increases downward. So, the coordinate 3,0 is to the right of 0,0,
 * and the coordinate 0,7 is below 0,0. The coordinates in this example form the following pattern,
 * where # is a dot on the paper and . is an empty, unmarked position:
 *
 * ...#..#..#.
 * ....#......
 * ...........
 * #..........
 * ...#....#.#
 * ...........
 * ...........
 * ...........
 * ...........
 * ...........
 * .#....#.##.
 * ....#......
 * ......#...#
 * #..........
 * #.#........
 * Then, there is a list of fold instructions. Each instruction indicates a line
 * on the transparent paper and wants you to fold the paper up (for horizontal y=... lines)
 * or left (for vertical x=... lines). In this example, the first fold instruction is fold along y=7,
 * which designates the line formed by all of the positions where y is 7 (marked here with -):
 *
 * ...#..#..#.
 * ....#......
 * ...........
 * #..........
 * ...#....#.#
 * ...........
 * ...........
 * -----------
 * ...........
 * ...........
 * .#....#.##.
 * ....#......
 * ......#...#
 * #..........
 * #.#........
 * Because this is a horizontal line, fold the bottom half up. Some of the dots might end up
 * overlapping after the fold is complete, but dots will never appear exactly on a fold line.
 * The result of doing this fold looks like this:
 *
 * #.##..#..#.
 * #...#......
 * ......#...#
 * #...#......
 * .#.#..#.###
 * ...........
 * ...........
 * Now, only 17 dots are visible.
 *
 * Notice, for example, the two dots in the bottom left corner before the transparent paper is folded;
 * after the fold is complete, those dots appear in the top left corner (at 0,0 and 0,1).
 * Because the paper is transparent, the dot just below them in the result (at 0,3) remains
 * visible, as it can be seen through the transparent paper.
 *
 * Also notice that some dots can end up overlapping; in this case, the dots merge together and become a single dot.
 *
 * The second fold instruction is fold along x=5, which indicates this line:
 *
 * #.##.|#..#.
 * #...#|.....
 * .....|#...#
 * #...#|.....
 * .#.#.|#.###
 * .....|.....
 * .....|.....
 * Because this is a vertical line, fold left:
 *
 * #####
 * #...#
 * #...#
 * #...#
 * #####
 * .....
 * .....
 * The instructions made a square!
 *
 * The transparent paper is pretty big, so for now, focus on just completing the first fold.
 * After the first fold in the example above, 17 dots are visible - dots that end up
 * overlapping after the fold is completed count as a single dot.
 *
 * How many dots are visible after completing just the first fold instruction on your transparent paper?
 *
 * Your puzzle answer was 759.
 */

public class Day13 {

    public static List<Dot> dots = new ArrayList<>();

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv13.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int solution = 0;

            boolean canReadFolds = false;

            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    canReadFolds = true;
                    continue;
                }
                if (canReadFolds) {
                    String[] foldParts = line.replace("fold along ", "").split("=");
                    makeFold(foldParts[0], Integer.parseInt(foldParts[1]));
                    dots.sort((d1, d2) -> d1.compareTo(d2));
                    removeDuplicities();
                    solution = dots.size();
                    break;
                } else {
                    String[] parts = line.split(",");
                    dots.add(new Dot(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
                }
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("number of dots after first fold = " + solution);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void makeFold(String axis, int num) {
        if (axis.equals("x")) {
            for (Dot dot : dots) {
                if (dot.getX() > num) {
                    dot.setX(num - (dot.getX() - num));
                }
            }
        } else if (axis.equals("y")) {
            for (Dot dot : dots) {
                if (dot.getY() > num) {
                    dot.setY((num - (dot.getY() - num)));
                }
            }
        }
    }

    public static void removeDuplicities() {
        ListIterator<Dot> iter = dots.listIterator();
        Dot d1 = iter.next();
        Dot d2;
        while (iter.hasNext()) {
            d2 = iter.next();
            if (d1.equals(d2)) {
                iter.remove();
            } else {
                d1 = d2;
            }
        }
    }
}