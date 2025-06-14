package eth.epieffe.jwalker.example.npuzzle;

/**
 * Methods that implement consistent heuristics for {@link NPuzzle}.
 */
public class NPuzzleHeuristics {

    private NPuzzleHeuristics() {}

    /**
     * Returns the sum of the Manhattan distances from each cell of the
     * provided {@link NPuzzle} instance to its target position.
     *
     * @param nPuzzle an instance of {@link NPuzzle}
     * @return the sum of the Manhattan distances from each cell to its target
     * position
     * @throws NullPointerException if nPuzzle is {@code null}
     */
    public static int manhattanSum(NPuzzle nPuzzle) {
        int sum = 0;
        for (int row = 0; row < nPuzzle.size; row++) {
            for (int col = 0; col < nPuzzle.size; col++) {
                int cell = nPuzzle.cell(row, col);
                if (cell == NPuzzle.EMPTY_CELL) {
                    cell = nPuzzle.size * nPuzzle.size;
                }
                int targetCol = (cell - 1) % nPuzzle.size;
                int targetRow = (cell - 1) / nPuzzle.size;
                sum += Math.abs(col - targetCol) + Math.abs(row - targetRow);
            }
        }
        return sum;
    }

    /**
     * Returns the number of cells in the provided {@link NPuzzle} instance
     * that are out of place.
     *
     * @param nPuzzle an instance of {@link NPuzzle}
     * @return the number of out of place cells in nPuzzle
     * @throws NullPointerException if nPuzzle is {@code null}
     */
    public static int outOfPlace(NPuzzle nPuzzle) {
        int count = 0;
        for (byte row = 0; row < nPuzzle.size; row++) {
            for (byte col = 0; col < nPuzzle.size; col++) {
                int cell = nPuzzle.cell(row, col);
                if (cell == NPuzzle.EMPTY_CELL) {
                    cell = nPuzzle.size * nPuzzle.size;
                }
                if (cell != nPuzzle.size * row + col + 1) {
                    ++count;
                }
            }
        }
        return count;
    }
}
