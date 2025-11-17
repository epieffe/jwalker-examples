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
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NPuzzleGraphTest {

    @Test
    public void testOutgoingEdges() {
        NPuzzle nPuzzle;
        List<Edge<NPuzzle>> edges;
        nPuzzle = NPuzzle.newInstance(7, 8, 3, 2, 1, 5, 4, 6, 0);
        edges = NPuzzleGraph.INSTANCE.outgoingEdges(nPuzzle);
        assertEquals(2, edges.size());
        assertTrue(edges.contains(new Edge<>(
                NPuzzle.newInstance(7, 8, 3, 2, 1, 5, 4, 0, 6),
                1,
                "LEFT"
        )));
        assertTrue(edges.contains(new Edge<>(
                NPuzzle.newInstance(7, 8, 3, 2, 1, 0, 4, 6, 5),
                1,
                "UP"
        )));

        nPuzzle = NPuzzle.newInstance(0, 6, 3, 4, 1, 2, 7, 5, 8);
        edges = NPuzzleGraph.INSTANCE.outgoingEdges(nPuzzle);
        assertEquals(2, edges.size());
        assertTrue(edges.contains(new Edge<>(
                NPuzzle.newInstance(6, 0, 3, 4, 1, 2, 7, 5, 8),
                1,
                "RIGHT"
        )));
        assertTrue(edges.contains(new Edge<>(
                NPuzzle.newInstance(4, 6, 3, 0, 1, 2, 7, 5, 8),
                1,
                "DOWN"
        )));

        nPuzzle = NPuzzle.newInstance(5, 4, 1, 3, 6, 2, 8, 0, 7);
        edges = NPuzzleGraph.INSTANCE.outgoingEdges(nPuzzle);
        assertEquals(3, edges.size());
        assertTrue(edges.contains(new Edge<>(
                NPuzzle.newInstance(5, 4, 1, 3, 6, 2, 0, 8, 7),
                1,
                "LEFT"
        )));
        assertTrue(edges.contains(new Edge<>(
                NPuzzle.newInstance(5, 4, 1, 3, 6, 2, 8, 7, 0),
                1,
                "RIGHT"
        )));
        assertTrue(edges.contains(new Edge<>(
                NPuzzle.newInstance(5, 4, 1, 3, 0, 2, 8, 6, 7),
                1,
                "UP"
        )));

        nPuzzle = NPuzzle.newInstance(2, 3, 5, 7, 0, 1, 6, 4, 8);
        edges = NPuzzleGraph.INSTANCE.outgoingEdges(nPuzzle);
        assertEquals(4, edges.size());
        assertTrue(edges.contains(new Edge<>(
                NPuzzle.newInstance(2, 3, 5, 0, 7, 1, 6, 4, 8),
                1,
                "LEFT"
        )));
        assertTrue(edges.contains(new Edge<>(
                NPuzzle.newInstance(2, 3, 5, 7, 1, 0, 6, 4, 8),
                1,
                "RIGHT"
        )));
        assertTrue(edges.contains(new Edge<>(
                NPuzzle.newInstance(2, 0, 5, 7, 3, 1, 6, 4, 8),
                1,
                "UP"
        )));
    }
}
