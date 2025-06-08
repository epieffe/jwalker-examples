package eth.epieffe.jwalker.example.npuzzle;

import eth.epieffe.jwalker.Edge;
import eth.epieffe.jwalker.Graph;
import eth.epieffe.jwalker.Heuristic;
import eth.epieffe.jwalker.Visit;
import eth.epieffe.jwalker.Visits;

import java.util.List;

public class Main {

    private static final int SIZE = 4;

    public static void main(String... args) {
        NPuzzle start = NPuzzle.newRandomInstance(SIZE, true);
        System.out.println("Initial configuration:");
        System.out.println(Util.toPrettyString(start));
        // Search for a path from start to target
        List<Edge<NPuzzle>> path = solve(start);
        if (path == null) {
            System.err.println("No solution found");
            return;
        }
        // Print moves sequence
        for (int i = 0; i < path.size(); ++i) {
            Edge<NPuzzle> edge = path.get(i);
            System.out.println("Move " + (i + 1) + ": " + edge.label);
            System.out.println(Util.toPrettyString(edge.destination));
        }
    }

    private static List<Edge<NPuzzle>> solve(NPuzzle nPuzzle) {
        Graph<NPuzzle> graph = NPuzzleGraph.INSTANCE;
        Heuristic<NPuzzle> heuristic = NPuzzleHeuristics::manhattanSum;
        Visit<NPuzzle> visit = Visits.aStar(graph, heuristic, 2);
        return visit.run(nPuzzle);
    }
}
