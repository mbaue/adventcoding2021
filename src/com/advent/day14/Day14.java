package com.advent.day14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * https://adventofcode.com/2021/day/14
 *
 * --- Day 14: Extended Polymerization ---
 * The incredible pressures at this depth are starting to put a strain on your submarine.
 * The submarine has polymerization equipment that would produce suitable materials to reinforce
 * the submarine, and the nearby volcanically-active caves should even have the necessary
 * input elements in sufficient quantities.
 *
 * The submarine manual contains instructions for finding the optimal polymer formula;
 * specifically, it offers a polymer template and a list of pair insertion rules (your puzzle input).
 * You just need to work out what polymer would result after repeating the pair insertion process a few times.
 *
 * For example:
 *
 * NNCB
 *
 * CH -> B
 * HH -> N
 * CB -> H
 * NH -> C
 * HB -> C
 * HC -> B
 * HN -> C
 * NN -> C
 * BH -> H
 * NC -> B
 * NB -> B
 * BN -> B
 * BB -> N
 * BC -> B
 * CC -> N
 * CN -> C
 * The first line is the polymer template - this is the starting point of the process.
 *
 * The following section defines the pair insertion rules. A rule like AB -> C means
 * that when elements A and B are immediately adjacent, element C should be inserted between them.
 * These insertions all happen simultaneously.
 *
 * So, starting with the polymer template NNCB, the first step simultaneously considers all three pairs:
 *
 * The first pair (NN) matches the rule NN -> C, so element C is inserted between the first N and the second N.
 * The second pair (NC) matches the rule NC -> B, so element B is inserted between the N and the C.
 * The third pair (CB) matches the rule CB -> H, so element H is inserted between the C and the B.
 * Note that these pairs overlap: the second element of one pair is the first element of the next pair.
 * Also, because all pairs are considered simultaneously, inserted elements are not considered
 * to be part of a pair until the next step.
 *
 * After the first step of this process, the polymer becomes NCNBCHB.
 *
 * Here are the results of a few steps using the above rules:
 *
 * Template:     NNCB
 * After step 1: NCNBCHB
 * After step 2: NBCCNBBBCBHCB
 * After step 3: NBBBCNCCNBBNBNBBCHBHHBCHB
 * After step 4: NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB
 * This polymer grows quickly. After step 5, it has length 97; After step 10, it has length 3073.
 * After step 10, B occurs 1749 times, C occurs 298 times, H occurs 161 times, and N occurs 865 times;
 * taking the quantity of the most common element (B, 1749) and subtracting the quantity of
 * the least common element (H, 161) produces 1749 - 161 = 1588.
 *
 * Apply 10 steps of pair insertion to the polymer template and find the most and least common
 * elements in the result. What do you get if you take the quantity of the most common element
 * and subtract the quantity of the least common element?
 *
 * Your puzzle answer was 2602.
 */

public class Day14 {

    public static Map<String, Integer> letterList = new HashMap<>();

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv14.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            String template = br.readLine();
            br.readLine(); // empty line
            Map<String, String> rules = new HashMap<>();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" -> ");
                rules.put(parts[0], parts[1]);
            }
            String input = template;
            for (int cnt = 1; cnt <= 10; cnt++) {
                int inputLength = input.length();
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < inputLength; i++) {
                    String s = input.substring(i - 1, i + 1);
                    String sr = rules.get(s);
                    sb.append(s.charAt(0));
                    sb.append(sr);
                }
                sb.append(input.charAt(inputLength - 1));
                System.out.println(sb);
                input = sb.toString();
            }

            for (int i = 0; i < input.length(); i++) {
                String oneLetter = input.substring(i, i + 1);
                if (letterList.containsKey(oneLetter)) {
                    int count = letterList.get(oneLetter);
                    letterList.replace(oneLetter, count + 1);
                } else {
                    letterList.put(oneLetter, 1);
                }
            }

            Collection<Integer> vals = letterList.values();
            int max = Collections.max(vals);
            int min = Collections.min(vals);

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("most significant letter minus least significant letter = " + (max - min));
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}