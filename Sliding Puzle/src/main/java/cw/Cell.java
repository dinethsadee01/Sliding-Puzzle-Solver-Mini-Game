/**
 * 20212183/w1902037 - Dineth Sadeepa Edirisinghe
 */

package cw;

public class Cell {
    private int row;
    private int col;
    private char type;
    private int heuristicCost; // Estimated cost to reach the finish node
    private int gScore; // Cost from the start node to this node

    public boolean isStart() {
        return type == 'S';
    }

    public boolean isFinish() {
        return type == 'F';
    }

    public boolean isRock() {
        return type == '0';
    }

    public Cell(int row, int col, char type) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.heuristicCost = 0;
        this.gScore = Integer.MAX_VALUE;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setHeuristicCost(int heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    public int getGScore() {
        return gScore;
    }

    public void setGScore(int gScore) {
        this.gScore = gScore;
    }

    public int getTotalCost() {
        return gScore + heuristicCost;
    }

    @Override
    public String toString() {
        return "(" + (col+1) + "," + (row+1) + ")";
    }
}
