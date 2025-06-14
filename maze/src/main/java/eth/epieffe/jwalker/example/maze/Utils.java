package eth.epieffe.jwalker.example.maze;

import eth.epieffe.jwalker.Edge;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

class Utils {

    private static final Random random = new Random();

    /**
     * Creates a new maze using a randomized DFS.
     *
     * @param width the width of the new maze
     * @param height the height of the new maze
     * @return a new randomly generated maze
     * @throws IllegalArgumentException if width or height are less than or equal to zero
     */
    public static MazeGraph newMazeWithRandomizedDfs(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Invalid size");
        }
        byte[][] grid = new byte[height][width];
        int row = random.nextInt(height);
        if (row % 2 != 0) --row;
        int col = random.nextInt(width);
        if (col % 2 != 0) --col;

        randomizedDfs(row, col, grid);

        int targetRow = height - 2 + (height % 2);
        int targetCol = width - 2 + (width % 2);
        return new MazeGraph(grid, targetRow, targetCol);
    }

    /**
     * Prints a maze with the path from a starting cell to the target cell.
     */
    public static void prettyPrint(MazeGraph maze, Cell start, List<Edge<Cell>> path) {
        StringBuilder sb = new StringBuilder();
        sb.append('+').append("---".repeat(maze.width())).append("+\n");
        for (int row = 0; row < maze.height(); ++row) {
            sb.append('|');
            for (int col = 0; col < maze.width(); ++col) {
                if (row == start.row && col == start.col) {
                    sb.append(redColored(" S "));
                } else if (row == maze.targetRow && col == maze.targetCol) {
                    sb.append(redColored(" E "));
                } else {
                    if (maze.cell(row, col) == MazeGraph.BLOCKED_CELL) {
                        sb.append("XXX");
                    } else if (isCellInPath(row, col, path)) {
                        sb.append(redColored(" * "));
                    } else {
                        sb.append("   ");
                    }
                }
            }
            sb.append("|\n");
        }
        sb.append('+').append("---".repeat(maze.width())).append("+\n");
        System.out.println(sb);
    }

    private static void randomizedDfs(int row, int col, byte[][] grid) {
        grid[row][col] = 1;

        int[] dirRow = {-1, 0, 1, 0};
        int[] dirCol = {0, 1, 0, -1};
        int[] indexes = IntStream.range(0, dirRow.length).toArray();

        shuffle(indexes);

        for (int i : indexes) {
            int newRow = row + (dirRow[i] * 2);
            if (newRow < 0 || newRow >= grid.length) continue;
            int newCol = col + (dirCol[i] * 2);
            if (newCol < 0 || newCol >= grid[0].length) continue;
            if (grid[newRow][newCol] == 0) {
                grid[row + dirRow[i]][col + dirCol[i]] = 1;
                randomizedDfs(newRow, newCol, grid);
            }
        }
    }

    private static boolean isCellInPath(int row, int col, List<Edge<Cell>> path) {
        if (path == null) return false;
        for (Edge<Cell> edge : path) {
            if (edge.destination.row == row && edge.destination.col == col) {
                return true;
            }
        }
        return false;
    }

    private static void shuffle(int[] array) {
        for (int i = array.length - 1; i > 0; --i) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private static String redColored(String s) {
        return String.format("\u001B[31m%s\u001B[0m", s);
    }
}
