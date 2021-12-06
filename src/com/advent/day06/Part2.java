package com.advent.day06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * --- Day 6: Lanternfish ---
 * --- Part Two ---
 * Suppose the lanternfish live forever and have unlimited food and space.
 * Would they take over the entire ocean?
 *
 * After 256 days in the example above, there would be a total of 26984457539 lanternfish!
 *
 * How many lanternfish would there be after 256 days?
 *
 * Your puzzle answer was 1640526601595.
 */

public class Part2 {

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv06.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            long cnt0 = 0;
            long cnt1 = 0;
            long cnt2 = 0;
            long cnt3 = 0;
            long cnt4 = 0;
            long cnt5 = 0;
            long cnt6 = 0;
            long cnt7 = 0;
            long cnt8 = 0;

            String[] numberStr = new String[1];
            while ((line = br.readLine()) != null) {
                numberStr = line.split(",");
            }

            for (String s : numberStr) {
                switch (Integer.parseInt(s)) {
                    case 0 -> cnt0++;
                    case 1 -> cnt1++;
                    case 2 -> cnt2++;
                    case 3 -> cnt3++;
                    case 4 -> cnt4++;
                    case 5 -> cnt5++;
                    case 6 -> cnt6++;
                    case 7 -> cnt7++;
                    case 8 -> cnt8++;
                }
            }
            long sum;

            int cycleNum = 0;
            do {
                cycleNum++;
                long pom = cnt0;
                cnt0 = cnt1;
                cnt1 = cnt2;
                cnt2 = cnt3;
                cnt3 = cnt4;
                cnt4 = cnt5;
                cnt5 = cnt6;
                cnt6 = cnt7 + pom;
                cnt7 = cnt8;
                cnt8 = pom;
                sum = cnt0 + cnt1 + cnt2 + cnt3 + cnt4 + cnt5 + cnt6 + cnt7 + cnt8;
            } while (cycleNum < 256);

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("sum = " + sum);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}