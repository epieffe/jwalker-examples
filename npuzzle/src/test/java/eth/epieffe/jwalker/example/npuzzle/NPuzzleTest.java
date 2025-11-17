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
import eth.epieffe.jwalker.Visit;
import eth.epieffe.jwalker.Visits;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NPuzzleTest {

    @Test
    public void testNewInstance() {
        NPuzzle nPuzzle;
        nPuzzle = NPuzzle.newInstance(1, 4, 2, 7, 0, 6, 5, 3, 8);
        assertEquals(3, nPuzzle.size());
        assertEquals(1, nPuzzle.emptyRow());
        assertEquals(1, nPuzzle.emptyCol());
        assertEquals(4, nPuzzle.cell(0, 1));
        assertEquals(0, nPuzzle.cell(1, 1));

        nPuzzle = NPuzzle.newInstance(11, 3, 14, 8, 6, 12, 0, 5, 7, 1, 10, 4, 13, 9, 15, 2);
        assertEquals(4, nPuzzle.size());
        assertEquals(1, nPuzzle.emptyRow());
        assertEquals(2, nPuzzle.emptyCol());
        assertEquals(4, nPuzzle.cell(2, 3));
        assertEquals(9, nPuzzle.cell(3, 1));
    }

    @Test
    public void testNewRandomInstance() {
        NPuzzle nPuzzle = NPuzzle.newRandomInstance(3, true);
        assertEquals(3, nPuzzle.size());
        Visit<NPuzzle> visit = Visits.bestFirst(NPuzzleGraph.INSTANCE, NPuzzleHeuristics::manhattanSum);
        List<Edge<NPuzzle>> path = visit.run(nPuzzle);
        assertNotNull(path);
    }

    @Test
    public void testIsSolved() {
        NPuzzle nPuzzle;
        nPuzzle = NPuzzle.newInstance(0);
        assertTrue(nPuzzle.isSolved());

        nPuzzle = NPuzzle.newInstance(1, 2, 3, 0);
        assertTrue(nPuzzle.isSolved());

        nPuzzle = NPuzzle.newInstance(1, 2, 0, 3);
        assertFalse(nPuzzle.isSolved());

        nPuzzle = NPuzzle.newInstance(1, 2, 3, 4, 5, 6, 7, 8, 0);
        assertTrue(nPuzzle.isSolved());

        nPuzzle = NPuzzle.newInstance(0, 1, 2, 3, 4, 5, 6, 7, 8);
        assertFalse(nPuzzle.isSolved());

        nPuzzle = NPuzzle.newInstance(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0);
        assertTrue(nPuzzle.isSolved());

        nPuzzle = NPuzzle.newInstance(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0, 15);
        assertFalse(nPuzzle.isSolved());
    }
}
