package eth.epieffe.jwalker.example.nqueens;

import eth.epieffe.jwalker.Graph;
import eth.epieffe.jwalker.Heuristic;
import eth.epieffe.jwalker.LocalSearch;
import eth.epieffe.jwalker.LocalSearches;

import java.util.function.Supplier;

public class Main {

    private static final int SIZE = 8;

    public static void main(String... args) {
        System.out.format("Generating N-Queens solution on a %1$dx%1$d board:\n\n", SIZE);
        // Search for a solution
        NQueens sol = generateSolution();
        System.out.println(Util.toPrettyString(sol));
        int numThreats = NQueensHeuristics.numThreats(sol);
        System.out.println("is solved: " + (numThreats == 0));
        System.out.println("conflicts: " + numThreats);
    }

    private static NQueens generateSolution() {
        Graph<NQueens> graph = NQueensGraph.INSTANCE;
        Heuristic<NQueens> heuristic = NQueensHeuristics::numThreats;
        Supplier<NQueens> randomNodeSupplier = () -> NQueens.newRandomInstance(SIZE);
        LocalSearch<NQueens> search = LocalSearches.steepestDescent(graph, randomNodeSupplier, heuristic, 100);
        return search.run();
    }
}
