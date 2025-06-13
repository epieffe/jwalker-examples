package eth.epieffe.jwalker.example.maze;

import eth.epieffe.jwalker.Edge;
import eth.epieffe.jwalker.Heuristic;
import eth.epieffe.jwalker.Visit;
import eth.epieffe.jwalker.Visits;

import java.util.List;

public class Main {

    private static final int WIDTH = 27;
    private static final int HEIGHT = 19;

    public static void main(String... args) {
        System.out.format("Generating random %d x %d maze ...\n", WIDTH, HEIGHT);
        MazeGraph maze = Util.newMazeWithRandomizedDfs(WIDTH, HEIGHT);
        Cell start = Cell.newInstance(0, 0, maze);

        System.out.println("solving ...");
        List<Edge<Cell>> path = solve(maze, start);

        Util.prettyPrint(maze, start, path);
        System.out.println("Path length: " + path.size());
    }

    private static List<Edge<Cell>> solve(MazeGraph maze, Cell start) {
        MazeHeuristics heuristics = MazeHeuristics.fromMaze(maze);
        Heuristic<Cell> heuristic = heuristics::manhattan;
        Visit<Cell> visit = Visits.greedyBestFirst(maze, heuristic);
        return visit.run(start);
    }
}
