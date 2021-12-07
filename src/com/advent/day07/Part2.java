package com.advent.day07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * --- Day 7: The Treachery of Whales ---
 * --- Part Two ---
 * The crabs don't seem interested in your proposed solution.
 * Perhaps you misunderstand crab engineering?
 *
 * As it turns out, crab submarine engines don't burn fuel at a constant rate.
 * Instead, each change of 1 step in horizontal position costs 1 more unit of fuel
 * than the last: the first step costs 1, the second step costs 2, the third step costs 3, and so on.
 *
 * As each crab moves, moving further becomes more expensive. This changes the
 * best horizontal position to align them all on; in the example above, this becomes 5:
 *
 * Move from 16 to 5: 66 fuel
 * Move from 1 to 5: 10 fuel
 * Move from 2 to 5: 6 fuel
 * Move from 0 to 5: 15 fuel
 * Move from 4 to 5: 1 fuel
 * Move from 2 to 5: 6 fuel
 * Move from 7 to 5: 3 fuel
 * Move from 1 to 5: 10 fuel
 * Move from 2 to 5: 6 fuel
 * Move from 14 to 5: 45 fuel
 * This costs a total of 168 fuel. This is the new cheapest possible outcome;
 * the old alignment position (2) now costs 206 fuel instead.
 *
 * Determine the horizontal position that the crabs can align to using the least
 * fuel possible so they can make you an escape route! How much fuel must
 * they spend to align to that position?
 *
 * Your puzzle answer was 86397080.
 */

public class Part2 {

    public static int[] input;

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv07.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            input = Arrays.stream(line.split(",")).mapToInt(s -> Integer.parseInt(s)).sorted().toArray();
            System.out.println("serazene pole = " + Arrays.toString(input));

            int min = input[0];
            int max = input[input.length-1];
            System.out.println("input[0] = " + min);
            System.out.println("input[max] = " + max);

            int minFuel = Integer.MAX_VALUE;
            for (int i = min; i <= max; i++) {
                int x = countFuel(i);
                if (x < minFuel) {
                    minFuel = x;
                }
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("minFuel = " + minFuel);
            System.out.println("----------------------------------------------------------------");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int countFuel(int position) {
        int sum = 0;
        for (int k : input) {
            int x = Math.abs(k - position);
            for (int j = x; j >= 1; j--) {
                sum += j;
            }
        }
        return sum;
    }
}
