/**
 * 20212183/w1902037 - Dineth Sadeepa Edirisinghe
 */

package cw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SlidingPuzzle {
    private Cell[][] grid;
    private int[] start;
    private int[] finish;

    public void loadMapFromFile(String filePath) throws IOException {
        List<Cell[]> rows = new ArrayList<>();
        int startRow = -1, startCol = -1;
        int finishRow = -1, finishCol = -1;

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int rowNum = 0;

        while ((line = reader.readLine()) != null) {
            Cell[] cells = new Cell[line.length()];

            for (int col = 0; col < line.length(); col++) {
                char type = line.charAt(col);
                Cell cell = new Cell(rowNum, col, type);
                cells[col] = cell;

                if (type == 'S') {
                    startRow = rowNum;
                    startCol = col;
                } else if (type == 'F') {
                    finishRow = rowNum;
                    finishCol = col;
                }
            }

            rows.add(cells);
            rowNum++;
        }
        reader.close();

        if (rows.isEmpty()) {
            throw new IllegalArgumentException("File is empty or not found: " + filePath);
        }

        // Convert List of Cell[] to 2D array
        int numRows = rows.size();
        int numCols = rows.get(0).length;
        grid = new Cell[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            grid[i] = rows.get(i);
        }

        start = new int[]{startRow, startCol};
        finish = new int[]{finishRow, finishCol};
    }

    public List<Cell> findShortestPath() {
        PriorityQueue<Cell> openSet = new PriorityQueue<>(Comparator.comparingInt(Cell::getTotalCost));
        Set<Cell> closedSet = new HashSet<>();
        Map<Cell, Cell> parentMap = new HashMap<>();

        Cell startCell = grid[start[0]][start[1]];
        Cell finishCell = grid[finish[0]][finish[1]];

        openSet.offer(startCell);

        while (!openSet.isEmpty()) {
            Cell current = openSet.poll();

            if (current == finishCell) {
                List<Cell> path = new ArrayList<>();
                Cell node = current;
                while (node != null) {
                    path.add(node);
                    node = parentMap.get(node);
                }
                Collections.reverse(path);
                return path;
            }

            closedSet.add(current);

            for (Cell neighbor : generateDynamicNeighbors(current)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeGScore = current.getGScore() + 1; // Assuming uniform cost for moves
                boolean isNewPath = false;

                if (!openSet.contains(neighbor)) {
                    openSet.offer(neighbor);
                    isNewPath = true;
                } else if (tentativeGScore < neighbor.getGScore()) {
                    isNewPath = true;
                }

                if (isNewPath) {
                    parentMap.put(neighbor, current);
                    neighbor.setGScore(tentativeGScore);
                    neighbor.setHeuristicCost(calculateHeuristic(neighbor, finishCell));
                }
            }
        }

        return Collections.emptyList(); // No path found
    }

    private int calculateHeuristic(Cell from, Cell to) {
        // Heuristic function using Manhattan distance
        return Math.abs(from.getRow() - to.getRow()) + Math.abs(from.getCol() - to.getCol());
    }

    private List<Cell> generateDynamicNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int newRow = cell.getRow();
            int newCol = cell.getCol();

            // Keep sliding until hitting a wall or a rock
            while (isValid(newRow + dir[0], newCol + dir[1])) {
                newRow += dir[0];
                newCol += dir[1];

                // Stop if hitting the start or finish cell
                if (grid[newRow][newCol].isStart() || grid[newRow][newCol].isFinish()) {
                    break;
                }
            }

            // Add the final stopping position as a neighbor
            neighbors.add(grid[newRow][newCol]);
        }

        return neighbors;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length
                && !grid[row][col].isRock(); // Exclude rocks
    }
}
