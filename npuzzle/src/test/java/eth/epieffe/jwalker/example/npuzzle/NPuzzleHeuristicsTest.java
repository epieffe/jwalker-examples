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
        assertEquals(8, NPuzzleHeuristics.manhattanSum(nPuzzle));

        nPuzzle = NPuzzle.newInstance(1, 8, 3, 6, 7, 4, 5, 2, 0);
        assertEquals(12, NPuzzleHeuristics.manhattanSum(nPuzzle));

        nPuzzle = NPuzzle.newInstance(3, 8, 1, 6, 5, 4, 0, 2, 7);
        assertEquals(16, NPuzzleHeuristics.manhattanSum(nPuzzle));

        nPuzzle = NPuzzle.newInstance(0, 2, 3, 13, 5, 6, 7, 8, 9, 10, 11, 12, 4, 14, 15, 1);
        assertEquals(24, NPuzzleHeuristics.manhattanSum(nPuzzle));
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
