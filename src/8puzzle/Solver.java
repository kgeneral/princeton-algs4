import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
We define a search node of the game to be a board, the number of moves made to reach the board, and the previous search node.
First, insert the initial search node (the initial board, 0 moves, and a null previous search node) into a priority queue.
Then, delete from the priority queue the search node with the minimum priority,
and insert onto the priority queue all neighboring search nodes (those that can be reached in one move from the dequeued search node).
Repeat this procedure until the search node dequeued corresponds to a goal board.
The success of this approach hinges on the choice of priority function for a search node.
* */
public class Solver {

	private MinPQ<SolvableBoard> minPQ;
	private int moves = 0;
	private List<Board> solution;

	// find a solution to the initial board (using the A* algorithm)
	// TODO : remove board if another optimal path finded
	public Solver(Board initial) {
		Set<SolvableBoard> costSoFar = new HashSet<>();
		Map<Board, Board> cameFrom = new HashMap<>();
		solution = new ArrayList<>();
		minPQ = new MinPQ<>();

		minPQ.insert(new SolvableBoard(initial, moves));
		Board goal = null;
		while (!minPQ.isEmpty()) {
			SolvableBoard current = minPQ.delMin();
			// int costSoFar = current.priority();
			// solution.add(dequeued);
			if (current.isGoal()) {
				goal = current.getBoard();
				break;
			}
			moves = current.getMoved() + 1;
			for (Board neighbor : current.getBoard().neighbors()) {
				SolvableBoard neighborBoard = new SolvableBoard(neighbor, moves);
				int newCost = neighborBoard.priority();

				if (!costSoFar.contains(neighborBoard) || newCost < neighborBoard.priority()) {
					costSoFar.add(neighborBoard);

					minPQ.insert(neighborBoard);
					// cameFrom.put(key, value)
				}

			}

		}
		
		if(goal == null)
			moves = -1;
		

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
		// create initial board from file
		In in = new In(args[0]);
		int N = in.readInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}

	private class SolvableBoard implements Comparable<SolvableBoard> {
		private Board board;
		private int moved;

		public SolvableBoard(Board board, int moved) {
			this.board = board;
			this.moved = moved;
		}

		public int getMoved() {
			return moved;
		}

		public int priority() {
			return board.manhattan() + moved;
		}

		// TODO : tune priority condition
		@Override
		public int compareTo(SolvableBoard that) {
			if (this.moved == that.moved) {

				if (this.priority() == that.priority()) {
					if (this.board.hamming() == that.board.hamming())
						return 0;

					return (this.board.hamming() > that.board.hamming()) ? -1 : 1;
				}

				return (this.priority() < that.priority()) ? -1 : 1;
			}
			if (this.moved < that.moved)
				return 1;
			return -1;
		}

		public boolean isGoal() {
			return board.isGoal();
		}

		public Iterable<Board> neighbors() {
			return board.neighbors();
		}

		public Board getBoard() {
			return board;
		}
	}
}