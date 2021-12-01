package com.advent.day01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv01.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int first = 0;
            int second = 0;
            int third = 0;
            int prevSum;
            int succSum;
            int countIncreased = 0;
            first  = Integer.valueOf(br.readLine());
            second = Integer.valueOf(br.readLine());
            third = Integer.valueOf(br.readLine());
            succSum = first + second + third;

            while ((line = br.readLine()) != null) {
                prevSum = succSum;
                first = second;
                second = third;
                third = Integer.valueOf(line);
                succSum = first + second + third;
                if (succSum > prevSum) {
                    countIncreased++;
                }
                //System.out.println(line);

            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("number of increased depth = " + countIncreased);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (
                IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
