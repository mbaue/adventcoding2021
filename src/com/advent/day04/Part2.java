package com.advent.day04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * --- Day 4: Giant Squid ---
 * --- Part Two ---
 * On the other hand, it might be wise to try a different strategy:
 * let the giant squid win.
 *
 * You aren't sure how many bingo boards a giant squid could play at once,
 * so rather than waste time counting its arms, the safe thing to do is to
 * figure out which board will win last and choose that one. That way,
 * no matter which boards it picks, it will win for sure.
 *
 * In the above example, the second board is the last to win, which happens
 * after 13 is eventually called and its middle column is completely marked.
 * If you were to keep playing until this point, the second board would have
 * a sum of unmarked numbers equal to 148 for a final score of 148 * 13 = 1924.
 *
 * Figure out which board will win last. Once it wins, what would its final score be?
 *
 * Your puzzle answer was 13158.
 */

public class Part2 {

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv04.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            int solution = 0;

            List<String> numbers = Arrays.stream(br.readLine().split(",")).toList();
            List<Bingo> bingos = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                Bingo b;
                if (line.equals("")) {
                    b = new Bingo();
                    for (int i = 0; i < 5; i++) {
                        b.fillLine(br.readLine(), i);
                    }
                    bingos.add(b);
                }
            }

            int num = 0;
            outerloop:
            for (String s : numbers) {
                int numDrown = Integer.parseInt(s);
                ListIterator<Bingo> iter = bingos.listIterator();
                while (iter.hasNext()) {
                    Bingo b = iter.next();
                    if (b.markNumber(numDrown)) {
                        if (bingos.size() == 1) {
                            solution = b.countSum() * numDrown;
                            b.printBingo();
                            System.out.println("tazene cislo " + numDrown);
                            break outerloop;
                        }
                        iter.remove();
                    }
                }
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("solution = " + solution);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}