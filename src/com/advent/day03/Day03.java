package com.advent.day03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 */

public class Day03 {
    public static void main(String[] args) {
        try {
            File file = new File("resources/adv03.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            //System.out.println("solution = " + solution);
            System.out.println("----------------------------------------------------------------");

            br.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
