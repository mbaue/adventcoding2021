package com.advent.day12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv12.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            Graph g = new Graph();

            while ((line = br.readLine()) != null) {
                String[] nodes = line.split("-");
                Node n1 = new Node(nodes[0]);
                Node n2 = new Node(nodes[1]);
                g.addNode(n1);
                g.addNode(n2);
                g.addEdge(n1, n2);
            }

            g.print();
            System.out.println("-----------------------------------------------------------------------------------------------------");

            int pathsCount = g.findPathsFrom2("start", "end");

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("number of paths = " + pathsCount);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
