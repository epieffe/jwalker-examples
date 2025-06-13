package eth.epieffe.jwalker.example.maze;

/**
 * A class that implements heuristics for a {@link MazeGraph}.
 */
public class MazeHeuristics {

    private final int targetRow;
    private final int targetCol;

    /**
     * Creates a new {@code MazeHeuristics} object with the same target
     * cell as the provided {@link MazeGraph} instance.
     *
     * @param maze a {@link MazeGraph} instance
     * @return a new {@code MazeHeuristics} object with the same target cell
     * as the provided maze
     */
    public static MazeHeuristics fromMaze(MazeGraph maze) {
        return new MazeHeuristics(maze.targetRow, maze.targetCol);
    }

    /**
     * Creates a new {@code MazeHeuristics} object with the provided
     * target cell.
     *
     * @param target a {@link Cell} instance
     * @return a new {@code MazeHeuristics} object with the provided
     * target cell
     */
    public static MazeHeuristics fromTargetCell(Cell target) {
        return new MazeHeuristics(target.row, target.col);
    }

    /**
     * Allocates a new instance with the target cell at the provided
     * coordinates.
     *
     * @param targetRow the row index of the target cell
     * @param targetCol the column index of the target cell
     */
    public MazeHeuristics(int targetRow, int targetCol) {
        this.targetRow = targetRow;
        this.targetCol = targetCol;
    }

    /**
     * Returns the Manhattan distance from the provided cell to the
     * target cell.
     *
     * @param cell a {@link Cell} instance
     * @return the Manhattan distance from the provided cell to the target cell
     */
    public double manhattan(Cell cell) {
        return Math.abs(cell.row - targetRow) + Math.abs(cell.col - targetCol);
    }

    /**
     * Returns the Chebyshev distance from the provided cell to the
     * target cell.
     *
     * @param cell a {@link Cell} instance
     * @return the Chebyshev distance from the provided cell to the target cell
     */
    public double chebyshev(Cell cell) {
        return Math.max(Math.abs(cell.row - targetRow), Math.abs(cell.col - targetCol));
    }

    /**
     * Returns the Euclidean distance from the provided cell to the
     * target cell.
     *
     * @param cell a {@link Cell} instance
     * @return the Euclidean distance from the provided cell to the target cell
     */
    public double euclidean(Cell cell) {
        double dr = cell.row - targetRow;
        double dc = cell.col - targetCol;
        return Math.sqrt((dr * dr) + (dc * dc));
    }
}
