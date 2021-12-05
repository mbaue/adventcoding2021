package com.advent.day05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day05 {
    public static void main(String[] args) {
        try {
            File file = new File("resources/adv05.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;


            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] part = line.split(" -> ");
                int x1 = Integer.parseInt(part[0].substring(0,part[0].indexOf(',')));
             //   System.out.println(x1)
               // ;
                int y1 = Integer.parseInt(part[0].substring(part[0].indexOf(',')+1));
                //System.out.println(y1);
                int x2 = Integer.parseInt(part[1].substring(0,part[1].indexOf(',')));
                //System.out.println(x2);
                int y2 = Integer.parseInt(part[1].substring(part[1].indexOf(',')+1));
                //System.out.println(y2);
                if (x1==x2 || y1 == y2) {
                    System.out.println("stejne");
                    System.out.println(x1);
                    System.out.println(x2);
                    System.out.println(y1);
                    System.out.println(y2);
                }
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            //          System.out.println("solution = " + (gammaRate * epsilonRate));
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
