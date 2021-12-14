package com.advent.day10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * --- Day 10: Syntax Scoring ---
 * --- Part Two ---
 * Now, discard the corrupted lines. The remaining lines are incomplete.
 *
 * Incomplete lines don't have any incorrect characters - instead, they're missing some
 * closing characters at the end of the line. To repair the navigation subsystem, you just need
 * to figure out the sequence of closing characters that complete all open chunks in the line.
 *
 * You can only use closing characters (), ], }, or >), and you must add them in the correct order
 * so that only legal pairs are formed and all chunks end up closed.
 *
 * In the example above, there are five incomplete lines:
 *
 * [({(<(())[]>[[{[]{<()<>> - Complete by adding }}]])})].
 * [(()[<>])]({[<{<<[]>>( - Complete by adding )}>]}).
 * (((({<>}<{<{<>}{[]{[]{} - Complete by adding }}>}>)))).
 * {<[[]]>}<{[{[{[]{()[[[] - Complete by adding ]]}}]}]}>.
 * <{([{{}}[<[[[<>{}]]]>[]] - Complete by adding ])}>.
 * Did you know that autocomplete tools also have contests? It's true! The score is determined
 * by considering the completion string character-by-character. Start with a total score of 0.
 * Then, for each character, multiply the total score by 5 and then increase the total score by
 * the point value given for the character in the following table:
 *
 * ): 1 point.
 * ]: 2 points.
 * }: 3 points.
 * >: 4 points.
 * So, the last completion string above - ])}> - would be scored as follows:
 *
 * Start with a total score of 0.
 * Multiply the total score by 5 to get 0, then add the value of ] (2) to get a new total score of 2.
 * Multiply the total score by 5 to get 10, then add the value of ) (1) to get a new total score of 11.
 * Multiply the total score by 5 to get 55, then add the value of } (3) to get a new total score of 58.
 * Multiply the total score by 5 to get 290, then add the value of > (4) to get a new total score of 294.
 * The five lines' completion strings have total scores as follows:
 *
 * }}]])})] - 288957 total points.
 * )}>]}) - 5566 total points.
 * }}>}>)))) - 1480781 total points.
 * ]]}}]}]}> - 995444 total points.
 * ])}> - 294 total points.
 * Autocomplete tools are an odd bunch: the winner is found by sorting all of the scores
 * and then taking the middle score. (There will always be an odd number of scores to consider.)
 * In this example, the middle score is 288957 because there are the same number
 * of scores smaller and larger than it.
 *
 * Find the completion string for each incomplete line, score the completion strings, and sort the scores. What is the middle score?
 *
 * Your puzzle answer was 1952146692.
 */

public class Part2 {

    public static final int ROUND = 1; //()
    public static final int SQUARE = 2; //[]
    public static final int CURLY = 3; //{}
    public static final int ANGLE = 4; //<>

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv10.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            long solution;
            List<Long> sums = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String result = processLine(line);
                if (!result.equals("")) {
                    long partSolution = 0;
                    for (int i = 0; i <result.length(); i++) {
                        String myChar = result.substring(i, i + 1);
                        switch (myChar) {
                            case "(" -> partSolution = partSolution * 5 + ROUND;
                            case "{" -> partSolution = partSolution * 5 + CURLY;
                            case "[" -> partSolution = partSolution * 5 + SQUARE;
                            case "<" -> partSolution = partSolution * 5 + ANGLE;
                        }
                    }
                    sums.add(partSolution);
                }
            }

            Object[] arr = sums.stream().sorted().toArray();
            int indexOfSolution = arr.length/2;
            solution = (long) arr[indexOfSolution];

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("sum for middle result = " + solution);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (
                IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String processLine(String line) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            String oneChar = line.substring(i, i + 1);
            String x;
            String falseStr = "";
            switch (oneChar) {
                case "(":
                case "{":
                case "[":
                case "<":
                    stack.push(oneChar);
                    break;
                case ")":
                    x = stack.peek();
                    if (x.equals("(")) {
                        stack.pop();
                    } else {
                        return falseStr;
                    }
                    break;
                case "}":
                    x = stack.peek();
                    if (x.equals("{")) {
                        stack.pop();
                    } else {
                        return falseStr;
                    }
                    break;
                case "]":
                    x = stack.peek();
                    if (x.equals("[")) {
                        stack.pop();
                    } else {
                        return falseStr;
                    }
                    break;
                case ">":
                    x = stack.peek();
                    if (x.equals("<")) {
                        stack.pop();
                    } else {
                        return falseStr;
                    }
                    break;
                default:
                    return "err";
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}

