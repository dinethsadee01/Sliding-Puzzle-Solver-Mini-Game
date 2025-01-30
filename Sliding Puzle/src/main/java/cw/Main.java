/**
 * 20212183/w1902037 - Dineth Sadeepa Edirisinghe
 */

package cw;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n|||- WELCOME- |||\n\nEnter your puzzle file path: ");
        String filePath = scanner.nextLine();
        SlidingPuzzle puzzle = new SlidingPuzzle();

        try {
            // Load map from file
            puzzle.loadMapFromFile(filePath);

            Runtime runtime = Runtime.getRuntime();
            runtime.gc();

            long startTime = System.nanoTime();
            long beforeUsedMem = runtime.totalMemory() - runtime.freeMemory();
            List<Cell> path = puzzle.findShortestPath(); // Find the shortest path while tracking time and memory usage
            long afterUsedMem = runtime.totalMemory() - runtime.freeMemory();
            long algorithmDuration = System.nanoTime() - startTime;

            // Print path
            if (!path.isEmpty()) {
                System.out.println("\nSolution path as follows;\n");
                System.out.println("Start at " + path.getFirst());
                for (int i = 1; i < path.size(); i++) {
                    Cell prevCell = path.get(i - 1);
                    Cell currentCell = path.get(i);
                    String direction = "";

                    if (currentCell.getRow() > prevCell.getRow()) {
                        direction = "down";
                    } else if (currentCell.getRow() < prevCell.getRow()) {
                        direction = "up";
                    } else if (currentCell.getCol() > prevCell.getCol()) {
                        direction = "right";
                    } else if (currentCell.getCol() < prevCell.getCol()) {
                        direction = "left";
                    }

                    System.out.println(i + ". Move " + direction + " to " + currentCell);
                }
                System.out.println("Done! You found the Finish cell.");
                System.out.println("\nTime spent for path finding: " + Math.round(algorithmDuration / 1_000_000.0) + " ms.");
                System.out.println("Memory increased: " + ((afterUsedMem - beforeUsedMem)/1024L) + " KB.");
            } else {
                System.out.println("No path found!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IllegalArgumentException e) {
            System.out.println("File is empty or not found: " + filePath);
        } catch (IOException e) {
            System.out.println("File is empty or no such file found.");
        }
    }
}
