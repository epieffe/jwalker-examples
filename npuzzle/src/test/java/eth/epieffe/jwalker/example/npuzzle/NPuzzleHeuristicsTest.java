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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NPuzzleHeuristicsTest {

    @Test
    public void testManhattanSum() {
        NPuzzle nPuzzle;
        nPuzzle = NPuzzle.newInstance(1, 2, 3, 4, 5, 6, 7, 8, 0);
        assertEquals(0, NPuzzleHeuristics.manhattanSum(nPuzzle));

        nPuzzle = NPuzzle.newInstance(3, 2, 1, 4, 5, 6, 7, 8, 0);
        assertEquals(4, NPuzzleHeuristics.manhattanSum(nPuzzle));

        nPuzzle = NPuzzle.newInstance(7, 2, 3, 4, 5, 6, 1, 8, 0);
        assertEquals(4, NPuzzleHeuristics.manhattanSum(nPuzzle));

        nPuzzle = NPuzzle.newInstance(0, 2, 3, 4, 5, 6, 7, 8, 1);
        assertEquals(4, NPuzzleHeuristics.manhattanSum(nPuzzle));

        nPuzzle = NPuzzle.newInstance(8, 1, 2, 5, 0, 3, 4, 7, 6);
        assertEquals(10, NPuzzleHeuristics.manhattanSum(nPuzzle));

        nPuzzle = NPuzzle.newInstance(1, 8, 3, 6, 7, 4, 5, 2, 0);
        assertEquals(12, NPuzzleHeuristics.manhattanSum(nPuzzle));

        nPuzzle = NPuzzle.newInstance(3, 8, 1, 6, 5, 4, 0, 2, 7);
        assertEquals(14, NPuzzleHeuristics.manhattanSum(nPuzzle));

        nPuzzle = NPuzzle.newInstance(3, 8, 2, 6, 0, 5, 7, 1, 4);
        assertEquals(14, NPuzzleHeuristics.manhattanSum(nPuzzle));

        nPuzzle = NPuzzle.newInstance(0, 2, 3, 13, 5, 6, 7, 8, 9, 10, 11, 12, 4, 14, 15, 1);
        assertEquals(18, NPuzzleHeuristics.manhattanSum(nPuzzle));
    }

    @Test
    public void testOutOfPlace() {
        NPuzzle nPuzzle;
        nPuzzle = NPuzzle.newInstance(1, 2, 3, 4, 5, 6, 7, 8, 0);
        assertEquals(0, NPuzzleHeuristics.outOfPlace(nPuzzle));

        nPuzzle = NPuzzle.newInstance(3, 2, 1, 4, 5, 6, 7, 8, 0);
        assertEquals(2, NPuzzleHeuristics.outOfPlace(nPuzzle));

        nPuzzle = NPuzzle.newInstance(1, 7, 6, 4, 5, 3, 2, 8, 0);
        assertEquals(4, NPuzzleHeuristics.outOfPlace(nPuzzle));

        nPuzzle = NPuzzle.newInstance(1, 2, 3, 5, 4, 7, 6, 0, 8);
        assertEquals(6, NPuzzleHeuristics.outOfPlace(nPuzzle));

        nPuzzle = NPuzzle.newInstance(4, 15, 3, 1, 10, 6, 7, 9, 8, 5, 11, 12, 0, 14, 2, 13);
        assertEquals(10, NPuzzleHeuristics.outOfPlace(nPuzzle));
    }
}
