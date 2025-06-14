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
        MazeGraph maze = Utils.newMazeWithRandomizedDfs(WIDTH, HEIGHT);
        Cell start = Cell.newInstance(0, 0, maze);

        System.out.println("solving ...");
        List<Edge<Cell>> path = solve(maze, start);
        if (path == null) {
            System.err.println("No solution found");
            return;
        }

        Utils.prettyPrint(maze, start, path);
        System.out.println("Path length: " + path.size());
    }

    private static List<Edge<Cell>> solve(MazeGraph maze, Cell start) {
        MazeHeuristics heuristics = MazeHeuristics.fromMaze(maze);
        Heuristic<Cell> heuristic = heuristics::manhattan;
        Visit<Cell> visit = Visits.greedyBestFirst(maze, heuristic);
        return visit.run(start);
    }
}
