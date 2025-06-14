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
        Utils.prettyPrint(start);

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
            Utils.prettyPrint(edge.destination);
        }
    }

    private static List<Edge<NPuzzle>> solve(NPuzzle nPuzzle) {
        Graph<NPuzzle> graph = NPuzzleGraph.INSTANCE;
        Heuristic<NPuzzle> heuristic = NPuzzleHeuristics::manhattanSum;
        Visit<NPuzzle> visit = Visits.aStar(graph, heuristic, 2);
        return visit.run(nPuzzle);
    }
}
