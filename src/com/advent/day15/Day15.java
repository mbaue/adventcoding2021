package com.advent.day15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * https://adventofcode.com/2021/day/15
 *
 * --- Day 15: Chiton ---
 * You've almost reached the exit of the cave, but the walls are getting closer together.
 * Your submarine can barely still fit, though; the main problem is that the walls of the cave
 * are covered in chitons, and it would be best not to bump any of them.
 *
 * The cavern is large, but has a very low ceiling, restricting your motion to two dimensions.
 * The shape of the cavern resembles a square; a quick scan of chiton density produces a map of risk level
 * throughout the cave (your puzzle input). For example:
 *
 * 1163751742
 * 1381373672
 * 2136511328
 * 3694931569
 * 7463417111
 * 1319128137
 * 1359912421
 * 3125421639
 * 1293138521
 * 2311944581
 * You start in the top left position, your destination is the bottom right position, and you
 * cannot move diagonally. The number at each position is its risk level; to determine the total
 * risk of an entire path, add up the risk levels of each position you enter (that is, don't count
 * the risk level of your starting position unless you enter it; leaving it adds no risk to your total).
 *
 * Your goal is to find a path with the lowest total risk. In this example,
 * a path with the lowest total risk is highlighted here:
 *
 * 1163751742
 * 1381373672
 * 2136511328
 * 3694931569
 * 7463417111
 * 1319128137
 * 1359912421
 * 3125421639
 * 1293138521
 * 2311944581
 * The total risk of this path is 40 (the starting position is never entered, so its risk is not counted).
 *
 * What is the lowest total risk of any path from the top left to the bottom right?
 *
 * Your puzzle answer was 673.
 */

public class Day15 {

    public static String[] arr;
    public static final int GRAPH_DIMENSION = 100;

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv15.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            arr = new String[GRAPH_DIMENSION];
            int i = 0;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                arr[i] = line;
                i++;
            }

            Node startingNode = new Node(null, 0, 0);

            List<Node> visited = new ArrayList<>();
            List<Node> unvisited = new ArrayList<>();
            int solution = 0;

            startingNode.setDist(0);
            unvisited.add(startingNode);
            while (!unvisited.isEmpty()) {
                Node processedNode = Collections.min(unvisited);
                System.out.println("hledam sousedy uzlu " + processedNode);
                List<Node> neighborNodes = processedNode.getNeighborVertices();
                while (!neighborNodes.isEmpty()) {
                    Node actualNode = neighborNodes.get(0);
                    neighborNodes.remove(0);

                    if (!visited.contains(actualNode)) {
                        int newDist = processedNode.getDist() + getEdgeValue(actualNode.getX(), actualNode.getY());
                        if (!unvisited.contains(actualNode)) {
                            // pridat do unvisited a nastavit dist
                            actualNode.setDist(newDist);
                            actualNode.setPredecessor(processedNode);
                            unvisited.add(actualNode);
                        } else {
                            // nastavit dist toho nalezeneho
                            Node foundNode = unvisited.get(unvisited.indexOf(actualNode));
                            if (foundNode.setDist(newDist)) {
                                foundNode.setPredecessor(processedNode);
                            }
                        }
                    }
                }
                visited.add(processedNode);
                unvisited.remove(processedNode);
            }

            for (Node n : visited) {
                if (n.getX() == GRAPH_DIMENSION - 1 && n.getY() == GRAPH_DIMENSION - 1) {
                    solution = n.getDist();
                }
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("risk level  = " + solution);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static int getEdgeValue(int x, int y) {
        return Character.getNumericValue(arr[y].charAt(x));
    }

}