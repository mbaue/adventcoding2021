package com.advent.day13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * --- Day 13: Transparent Origami ---
 * --- Part Two ---
 * Finish folding the transparent paper according to the instructions.
 * The manual says the code is always eight capital letters.
 *
 * What code do you use to activate the infrared thermal imaging camera system?
 *
 * Your puzzle answer was HECRZKPR.
 */

public class Part2 {

    public static List<Dot> dots = new ArrayList<>();

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv13.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

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
                } else {
                    String[] parts = line.split(",");
                    dots.add(new Dot(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
                }
            }

            String[][] result = new String[40][10];
            for (int x = 0; x < 40; x++) {
                for (int y = 0; y < 10; y++) {
                    result[x][y] = " ";
                }
            }
            for (Dot d : dots) {
                result[d.getX()][d.getY()] = "#";
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            for (int y = 0; y < 6; y++) {
                for (int x = 0; x < 40; x++) {
                    System.out.print(result[x][y]);
                }
                System.out.println();
            }
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
