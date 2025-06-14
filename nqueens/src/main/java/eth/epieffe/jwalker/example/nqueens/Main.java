/*
 * Copyright 2025 Epifanio Ferrari
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eth.epieffe.jwalker.example.nqueens;

import eth.epieffe.jwalker.Graph;
import eth.epieffe.jwalker.Heuristic;
import eth.epieffe.jwalker.LocalSearch;
import eth.epieffe.jwalker.LocalSearches;

import java.util.function.Supplier;

public class Main {

    private static final int SIZE = 8;

    public static void main(String... args) {
        System.out.format("Generating N-Queens solution on a %1$d x %1$d board:\n\n", SIZE);
        NQueens sol = generateSolution();

        // Print solution to stdout
        Utils.prettyPrint(sol);
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
