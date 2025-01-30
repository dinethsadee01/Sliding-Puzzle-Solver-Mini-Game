# Sliding Puzzle Game  
**Author:** Dineth Sadeepa Edirisinghe  

## Overview  
This project implements the classic **Sliding Puzzle Game** with AI-based pathfinding. The game finds the shortest path from the **start ('S')** position to the **finish ('F')** on a grid with obstacles ('0') using the **A* search algorithm**.  

## Features  
- Dynamic grid-based puzzle loading from text files.  
- Implementation of the **A* algorithm** for efficient pathfinding.  
- Uses **priority queues** and **heuristics** (Manhattan distance) for optimal performance.  
- Supports customizable puzzles through external input files.  
- Performance optimized to handle various grid sizes.  

## Algorithm and Data Structures  
- **Algorithm Used:** A* search algorithm.  
  - Chosen for its balance of efficiency and optimality in finding the shortest path.  
  - Comparison: Outperforms BFS and DFS regarding speed and scalability for weighted grids.  
- **Data Structures:**  
  - Priority Queue: To dynamically prioritize grid cells based on the total cost.  
  - 2D Array: For storing the grid structure.  
  - HashMap: To keep track of parent nodes for path reconstruction.  

## Setup and Usage  
1. Clone the repository:  
   ```bash  
   git clone <your-github-repo-url>  
   cd sliding-puzzle-game  
   ```  
2. Add your puzzle input file in `.txt` format (e.g., `puzzle_40.txt`).  
3. Compile and run the program:  
   ```bash  
   javac cw/*.java  
   java cw.SlidingPuzzle <file_path>  
   ```  

## Input Format  
- The puzzle grid is read from a text file, where:  
  - `S`: Start cell  
  - `F`: Finish cell  
  - `0`: Obstacle/rock  
  - `.`: Walkable path  

### Example Input:  
```
S...0  
..0..  
.0...  
.....  
...F.  
```  

## Performance  
- The program has been tested on grids of various sizes:  
  - **puzzle_20:** ~3ms  
  - **puzzle_320:** ~26ms  
- Time complexity: **O(E + V log V)**, where E is the number of edges, and V is the number of vertices.  

## Future Improvements  
- Add a graphical user interface (GUI) for better user interaction.  
- Extend to support other pathfinding algorithms (e.g., Dijkstra).  
- Include unit tests for improved code reliability.  

## License  
This project is licensed under the MIT License. 
