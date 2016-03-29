import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;

/*
We define a search node of the game to be a board, the number of moves made to reach the board, and the previous search node.
First, insert the initial search node (the initial board, 0 moves, and a null previous search node) into a priority queue.
Then, delete from the priority queue the search node with the minimum priority,
and insert onto the priority queue all neighboring search nodes (those that can be reached in one move from the dequeued search node).
Repeat this procedure until the search node dequeued corresponds to a goal board.
The success of this approach hinges on the choice of priority function for a search node.
* */
public class Solver {

    private MinPQ<Board> minPQ;
    private int moves = 0;
    private List<Board> solution;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        solution = new ArrayList<>();
        minPQ = new MinPQ<>();
        minPQ.insert(initial);

        Board dequeued;
        while (true) {
            dequeued = minPQ.delMin();
            solution.add(dequeued);
            if(dequeued.isGoal()) break;
            for (Board neighbor : dequeued.neighbors())
                minPQ.insert(neighbor);
            moves++;
        }

    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return moves != -1;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return solution;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
    }
}