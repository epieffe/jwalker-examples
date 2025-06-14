package eth.epieffe.jwalker.example.npuzzle;

import java.util.Arrays;

/**
 * An instance of the <i>N-Puzzle</i> game.<p>
 *
 * <i>N-Puzzle</i> is a sliding puzzle consisting of a grid of numbered tiles with one
 * empty space. The goal is to order the tiles by sliding them into the empty space.
 */
public class NPuzzle {

    public static final byte EMPTY_CELL = 0;

    // Fields visibility is package private, they are
    // accessed from NPuzzleGraph and NPuzzleHeuristic.
    final byte size;
    final byte emptyIndex;
    final byte[] table;

    /**
     * Creates a new {@code NPuzzle} instance from an array of numbers.<p>
     *
     * Any number less than or equal to zero  represents the empty cell.
     *
     * @param numbers an array of numbers
     * @return a new {@code NPuzzle} instance
     * @throws NullPointerException if numbers is null
     * @throws IllegalArgumentException if numbers do not represent a valid N-Puzzle instance
     */
    public static NPuzzle newInstance(int... numbers) {
        return Utils.newNPuzzle(numbers);
    }

    /**
     * Creates a new random {@code NPuzzle} instance.
     *
     * @param size the size of the newly created instance
     * @param checkSolvable if true, only solvable instances are returned
     * @return a new random {@code NPuzzle} instance
     * @throws IllegalArgumentException if size is greater than {@link Byte#MAX_VALUE}
     */
    public static NPuzzle newRandomInstance(int size, boolean checkSolvable) {
        NPuzzle nPuzzle = Utils.newRandomNPuzzle(size);
        while (checkSolvable && !Utils.isSolvable(nPuzzle)) {
            nPuzzle = Utils.newRandomNPuzzle(size);
        }
        return nPuzzle;
    }

    // Constructor visibility is package private, it is
    // accessed by NPuzzleGraph
    NPuzzle(byte size, byte emptyIndex, byte[] table) {
        this.size = size;
        this.emptyIndex = emptyIndex;
        this.table = table;
    }

    /**
     * Returns the size of this instance.
     *
     * @return the size of this instance
     */
    public byte size() {
        return size;
    }

    /**
     * Returns the value of a cell in this instance.<p>
     *
     * The empty cell is represented as zero.
     *
     * @param row the row of the cell
     * @param col the column of the cell
     * @return the cell at the provided coordinates
     * @throws ArrayIndexOutOfBoundsException if the provided coordinates are out of range
     */
    public byte cell(int row, int col) {
        return table[row * size + col];
    }

    /**
     * Returns the column of the empty cell.
     *
     * @return the column of the empty cell
     */
    public int emptyCol() {
        return emptyIndex % size;
    }

    /**
     * Returns the row of the empty cell.
     *
     * @return the row of the empty cell
     */
    public int emptyRow() {
        return emptyIndex / size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NPuzzle)) return false;
        NPuzzle other = (NPuzzle) o;
        return Arrays.equals(this.table, other.table);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(table);
    }

    @Override
    public String toString() {
        return Arrays.toString(table);
    }
}
