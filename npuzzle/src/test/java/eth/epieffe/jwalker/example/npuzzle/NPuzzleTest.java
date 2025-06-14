package eth.epieffe.jwalker.example.npuzzle;

import eth.epieffe.jwalker.Edge;
import eth.epieffe.jwalker.Visit;
import eth.epieffe.jwalker.Visits;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        Visit<NPuzzle> visit = Visits.greedyBestFirst(NPuzzleGraph.INSTANCE, NPuzzleHeuristics::manhattanSum);
        List<Edge<NPuzzle>> path = visit.run(nPuzzle);
        assertNotNull(path);
    }
}
