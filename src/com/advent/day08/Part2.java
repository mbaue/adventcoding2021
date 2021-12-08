package com.advent.day08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * --- Day 8: Seven Segment Search ---
 * --- Part Two ---
 * Through a little deduction, you should now be able to determine the remaining digits.
 * Consider again the first example above:
 *
 * acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab |
 * cdfeb fcadb cdfeb cdbaf
 * After some careful analysis, the mapping between signal wires and segments only
 * make sense in the following configuration:
 *
 * dddd
 * e    a
 * e    a
 * ffff
 * g    b
 * g    b
 * cccc
 * So, the unique signal patterns would correspond to the following digits:
 *
 * acedgfb: 8
 * cdfbe: 5
 * gcdfa: 2
 * fbcad: 3
 * dab: 7
 * cefabd: 9
 * cdfgeb: 6
 * eafb: 4
 * cagedb: 0
 * ab: 1
 * Then, the four digits of the output value can be decoded:
 *
 * cdfeb: 5
 * fcadb: 3
 * cdfeb: 5
 * cdbaf: 3
 * Therefore, the output value for this entry is 5353.
 *
 * Following this same process for each entry in the second, larger example above,
 * the output value of each entry can be determined:
 *
 * fdgacbe cefdb cefbgd gcbe: 8394
 * fcgedb cgb dgebacf gc: 9781
 * cg cg fdcagb cbg: 1197
 * efabcd cedba gadfec cb: 9361
 * gecf egdcabf bgf bfgea: 4873
 * gebdcfa ecba ca fadegcb: 8418
 * cefg dcbef fcge gbcadfe: 4548
 * ed bcgafe cdgba cbgef: 1625
 * gbdfcae bgc cg cgb: 8717
 * fgae cfgab fg bagce: 4315
 * Adding all of the output values in this larger example produces 61229.
 *
 * For each entry, determine all of the wire/segment connections and decode the four-digit
 * output values. What do you get if you add up all of the output values?
 *
 * Your puzzle answer was 1070188.
 */

public class Part2 {

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv08.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int solution = 0;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] inputLine = line.split(" \\| ");

                List<String> code = convertInput(inputLine[0]);
                Map<String, String> codeTable = decode(code);
                System.out.println(codeTable);

                String[] codeNumbers = inputLine[1].split(" ");
                StringBuilder digitsDecoded = new StringBuilder();
                for (String n : codeNumbers) {
                    System.out.print(n + "    ");
                    System.out.print(orderString(n) + "...");
                    String digitFound = codeTable.get(orderString(n));
                    System.out.println(digitFound);

                    digitsDecoded.append(digitFound);
                }
                int numDecodedFromDigits = Integer.parseInt(digitsDecoded.toString());
                solution += numDecodedFromDigits;
                System.out.println("-----------------------------");
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("sum of decoded numbers = " + solution);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Map<String, String> decode(List<String> list) {
        List<String> mutable = new ArrayList<>(list);
        Map<String, String> m = new HashMap<>();
        /*
         1 ... jediný dvoupísmenný string
         7 ... jediný třípísmenný string
         4 ... jediný čtyřpísmenný string
         8 ... jediný osmipísmenný string
         3 ... jediný z pětipísmenných stringů, který obsahuje všechny segmenty znaku "1"
         5 ... jediný z pětipísmenných stringů, který obsahuje rozdíl čtyřky a jedničky (od čtyř segmentů "4" odeberu dva segmenty "1")
         2 ... zbývající z pětipísmenných
         0 ... jediný z šestipísmenných, který neobsahuje rozdíl čtyřky a jedničky (od čtyř segmentů "4" odeberu dva segmenty "1")
         6 ... jediný z šestipísmenných, který neobsahuje všechny segmenty "1"
         9 ... zbývající z šestipísmenných
         */
        String jednicka = mutable.get(0);
        mutable.remove(0);
        m.put(jednicka, "1");
        String sedmicka = mutable.get(0);
        m.put(sedmicka, "7");
        mutable.remove(0);
        String ctyrka = mutable.get(0);
        m.put(ctyrka, "4");
        mutable.remove(0);
        String osmicka = mutable.get(6);
        m.put(osmicka, "8");
        mutable.remove(6);
        String ctyriMinusJedna = subtraction(ctyrka, jednicka);

        String trojka;
        String petka;
        String dvojka;
        for (int i = 1; i <= 3; i++) {
            if (containsSegments(mutable.get(0), jednicka)) {
                trojka = mutable.get(0);
                m.put(trojka, "3");
            } else if (containsSegments(mutable.get(0), ctyriMinusJedna)) {
                petka = mutable.get(0);
                m.put(petka, "5");
            } else {
                dvojka = mutable.get(0);
                m.put(dvojka, "2");
            }
            mutable.remove(0);
        }

        String nula;
        String sestka;
        String devitka;
        for (int i = 1; i <= 3; i++) {
            if (!containsSegments(mutable.get(0), ctyriMinusJedna)) {
                nula = mutable.get(0);
                m.put(nula, "0");
            } else if (!containsSegments(mutable.get(0), jednicka)) {
                sestka = mutable.get(0);
                m.put(sestka, "6");
            } else {
                devitka = mutable.get(0);
                m.put(devitka, "9");
            }
            mutable.remove(0);
        }
        return m;
    }

    public static String orderString(String s) {
        char[] ch = s.toCharArray();
        Arrays.sort(ch);
        return new String(ch);
    }

    public static boolean containsSegments(String longNumber, String shortNumber) {
        for (int i = 0; i < shortNumber.length(); i++) {
            if (longNumber.indexOf(shortNumber.charAt(i)) < 0) {
                return false;
            }
        }
        return true;
    }

    public static List<String> convertInput(String s) {
        Comparator<String> byLength = Comparator.comparingInt(String::length);
        return Arrays.stream(s.trim().split(" ")).map(str -> orderString(str)).sorted(byLength).toList();
    }

    public static String subtraction(String firstStr, String secondStr) {
        StringBuilder sb = new StringBuilder(firstStr);
        for (int i = 0; i < secondStr.length(); i++) {
            int positionOfChar = sb.indexOf(String.valueOf(secondStr.charAt(i)));
            if (positionOfChar >= 0) {
                sb.deleteCharAt(positionOfChar);
            }
        }
        return sb.toString();
    }
}
