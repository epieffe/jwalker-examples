package eth.epieffe.jwalker.example.npuzzle;

import eth.epieffe.jwalker.Edge;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
                "LEFT",
                1,
                NPuzzle.newInstance(7, 8, 3, 2, 1, 5, 4, 0, 6)
        )));
        assertTrue(edges.contains(new Edge<>(
                "UP",
                1,
                NPuzzle.newInstance(7, 8, 3, 2, 1, 0, 4, 6, 5)
        )));

        nPuzzle = NPuzzle.newInstance(0, 6, 3, 4, 1, 2, 7, 5, 8);
        edges = NPuzzleGraph.INSTANCE.outgoingEdges(nPuzzle);
        assertEquals(2, edges.size());
        assertTrue(edges.contains(new Edge<>(
                "RIGHT",
                1,
                NPuzzle.newInstance(6, 0, 3, 4, 1, 2, 7, 5, 8)
        )));
        assertTrue(edges.contains(new Edge<>(
                "DOWN",
                1,
                NPuzzle.newInstance(4, 6, 3, 0, 1, 2, 7, 5, 8)
        )));

        nPuzzle = NPuzzle.newInstance(5, 4, 1, 3, 6, 2, 8, 0, 7);
        edges = NPuzzleGraph.INSTANCE.outgoingEdges(nPuzzle);
        assertEquals(3, edges.size());
        assertTrue(edges.contains(new Edge<>(
                "LEFT",
                1,
                NPuzzle.newInstance(5, 4, 1, 3, 6, 2, 0, 8, 7)
        )));
        assertTrue(edges.contains(new Edge<>(
                "RIGHT",
                1,
                NPuzzle.newInstance(5, 4, 1, 3, 6, 2, 8, 7, 0)
        )));
        assertTrue(edges.contains(new Edge<>(
                "UP",
                1,
                NPuzzle.newInstance(5, 4, 1, 3, 0, 2, 8, 6, 7)
        )));

        nPuzzle = NPuzzle.newInstance(2, 3, 5, 7, 0, 1, 6, 4, 8);
        edges = NPuzzleGraph.INSTANCE.outgoingEdges(nPuzzle);
        assertEquals(4, edges.size());
        assertTrue(edges.contains(new Edge<>(
                "LEFT",
                1,
                NPuzzle.newInstance(2, 3, 5, 0, 7, 1, 6, 4, 8)
        )));
        assertTrue(edges.contains(new Edge<>(
                "RIGHT",
                1,
                NPuzzle.newInstance(2, 3, 5, 7, 1, 0, 6, 4, 8)
        )));
        assertTrue(edges.contains(new Edge<>(
                "UP",
                1,
                NPuzzle.newInstance(2, 0, 5, 7, 3, 1, 6, 4, 8)
        )));
    }

    @Test
    public void testIsTarget() {
        NPuzzle nPuzzle;
        nPuzzle = NPuzzle.newInstance(0);
        assertTrue(NPuzzleGraph.INSTANCE.isTarget(nPuzzle));

        nPuzzle = NPuzzle.newInstance(1, 2, 3, 0);
        assertTrue(NPuzzleGraph.INSTANCE.isTarget(nPuzzle));

        nPuzzle = NPuzzle.newInstance(1, 2, 0, 3);
        assertFalse(NPuzzleGraph.INSTANCE.isTarget(nPuzzle));

        nPuzzle = NPuzzle.newInstance(1, 2, 3, 4, 5, 6, 7, 8, 0);
        assertTrue(NPuzzleGraph.INSTANCE.isTarget(nPuzzle));

        nPuzzle = NPuzzle.newInstance(0, 1, 2, 3, 4, 5, 6, 7, 8);
        assertFalse(NPuzzleGraph.INSTANCE.isTarget(nPuzzle));

        nPuzzle = NPuzzle.newInstance(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0);
        assertTrue(NPuzzleGraph.INSTANCE.isTarget(nPuzzle));

        nPuzzle = NPuzzle.newInstance(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0, 15);
        assertFalse(NPuzzleGraph.INSTANCE.isTarget(nPuzzle));
    }
}
