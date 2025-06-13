package eth.epieffe.jwalker.example.maze;

import java.util.Objects;

/**
 * A cell in a {@link MazeGraph}.
 */
public class Cell {

    public final int row;

    public final int col;

    /**
     * Creates a new {@code Cell} relative to a {@link MazeGraph}.
     *
     * @param row the row index of the new cell
     * @param col the column index of the new cell
     * @param maze a {@link MazeGraph} instance
     * @return a new cell
     * @throws IllegalArgumentException if the cell is blocked in the provided maze
     * @throws ArrayIndexOutOfBoundsException if the cell coordinates are out of range
     */
    public static Cell newInstance(int row, int col, MazeGraph maze) {
        if (maze.cell(row, col) == MazeGraph.BLOCKED_CELL) {
            throw new IllegalArgumentException("Cell is blocked");
        }
        return new Cell(row, col);
    }

    Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Returns the row of this cell.
     *
     * @return the row of this cell
     */
    public int row() {
        return row;
    }

    /**
     * Returns the column of this cell.
     *
     * @return the column of this cell
     */
    public int col() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell other = (Cell) o;
        return row == other.row && col == other.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return String.format("(row: %d, col: %d)", row, col);
    }

}
