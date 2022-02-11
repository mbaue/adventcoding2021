package com.advent.day14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * --- Day 14: Extended Polymerization ---
 * --- Part Two ---
 * The resulting polymer isn't nearly strong enough to reinforce the submarine.
 * You'll need to run more steps of the pair insertion process; a total of 40 steps should do it.
 *
 * In the above example, the most common element is B (occurring 2192039569602 times)
 * and the least common element is H (occurring 3849876073 times); subtracting these
 * produces 2188189693529.
 *
 * Apply 40 steps of pair insertion to the polymer template and find the most and least
 * common elements in the result. What do you get if you take the quantity of the most
 * common element and subtract the quantity of the least common element?
 *
 * hint for solution: https://skarlso.github.io/2021/12/14/aoc-day14/
 *
 * Your puzzle answer was 2942885922173.
 */

public class Part2 {
    public static Map<String, Long> letterList = new HashMap<>();
    public static Map<String, Long> inpPairs = new HashMap<>();
    public static Map<String, Long> outPairs = new HashMap<>();

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

            // divide template string into pairs and put in map
            for (int i = 0; i < template.length() - 1; i++) {
                String s = template.substring(i, i + 2);
                addOrIncrement(inpPairs, s, 1L);
            }

            // apply rules on pairs and put result into map
            for (int cnt = 1; cnt <= 40; cnt++) {
                System.out.println("++++++++++    " + cnt + ". kolo    +++++++++++++++++++++++++++++++++++++++++++++++");
                for (String s : inpPairs.keySet()) {
                    String x = rules.get(s);
                    long xCount = inpPairs.get(s);
                    addOrIncrement(outPairs, s.charAt(0) + x, xCount);
                    addOrIncrement(outPairs, x + s.charAt(1), xCount);
                }
                System.out.println(outPairs);
                System.out.println("size= " + outPairs.size());
                inpPairs = outPairs;
                outPairs = new HashMap<>();
            }


            System.out.println("----------------------------------------------------------------");
            for (Map.Entry<String, Long> e : inpPairs.entrySet()) {
                addOrIncrement(letterList, e.getKey().substring(0, 1), e.getValue());
            }
            String lastLetter = template.substring(template.length() - 1);
            System.out.println(".......last letter=" + lastLetter + " .........");
            addOrIncrement(letterList, lastLetter, 1L);

            Collection<Long> vals = letterList.values();
            System.out.println(letterList);
            long max = Collections.max(vals);
            long min = Collections.min(vals);

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("most significant letter minus least significant letter = " + (max - min));
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static void addOrIncrement(Map<String, Long> map, String key, long val) {
        if (map.containsKey(key)) {
            long cnt = map.get(key);
            map.replace(key, cnt + val);
        } else {
            map.put(key, val);
        }
    }
}
