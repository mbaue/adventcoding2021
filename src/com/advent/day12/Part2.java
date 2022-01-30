package com.advent.day12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * --- Day 12: Passage Pathing ---
 * --- Part Two ---
 * After reviewing the available paths, you realize you might have time to visit a single small cave twice.
 * Specifically, big caves can be visited any number of times, a single small cave can be visited at most twice,
 * and the remaining small caves can be visited at most once. However, the caves named start and end can only be
 * visited exactly once each: once you leave the start cave, you may not return to it, and once you reach
 * the end cave, the path must end immediately.
 *
 * Now, the 36 possible paths through the first example above are:
 *
 * start,A,b,A,b,A,c,A,end
 * start,A,b,A,b,A,end
 * start,A,b,A,b,end
 * start,A,b,A,c,A,b,A,end
 * start,A,b,A,c,A,b,end
 * start,A,b,A,c,A,c,A,end
 * start,A,b,A,c,A,end
 * start,A,b,A,end
 * start,A,b,d,b,A,c,A,end
 * start,A,b,d,b,A,end
 * start,A,b,d,b,end
 * start,A,b,end
 * start,A,c,A,b,A,b,A,end
 * start,A,c,A,b,A,b,end
 * start,A,c,A,b,A,c,A,end
 * start,A,c,A,b,A,end
 * start,A,c,A,b,d,b,A,end
 * start,A,c,A,b,d,b,end
 * start,A,c,A,b,end
 * start,A,c,A,c,A,b,A,end
 * start,A,c,A,c,A,b,end
 * start,A,c,A,c,A,end
 * start,A,c,A,end
 * start,A,end
 * start,b,A,b,A,c,A,end
 * start,b,A,b,A,end
 * start,b,A,b,end
 * start,b,A,c,A,b,A,end
 * start,b,A,c,A,b,end
 * start,b,A,c,A,c,A,end
 * start,b,A,c,A,end
 * start,b,A,end
 * start,b,d,b,A,c,A,end
 * start,b,d,b,A,end
 * start,b,d,b,end
 * start,b,end
 * The slightly larger example above now has 103 paths through it, and the even larger example now has 3509 paths through it.
 *
 * Given these new rules, how many paths through this cave system are there?
 *
 * Your puzzle answer was 91533.
 */

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
