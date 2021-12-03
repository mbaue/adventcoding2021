package com.advent.day03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Part2 {

    public static void main(String[] args) {



        List<String> lines = new ArrayList<>();

        try {
            File file = new File("resources/adv03.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            int[] pole = new int[12];

            while ((line = br.readLine()) != null) {
                lines.add(line);

            }



            System.out.println("-------------------SOLUTION-------------------------------------");
            //System.out.println("solution = " + (gammaRate * epsilonRate));
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
