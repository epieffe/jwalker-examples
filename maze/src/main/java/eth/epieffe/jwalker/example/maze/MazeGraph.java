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
package eth.epieffe.jwalker.example.maze;

import eth.epieffe.jwalker.Edge;
import eth.epieffe.jwalker.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link Graph} that represents a maze.<p>
 *
 * A maze consists of a grid of cells and a target cell. Some cells are blocked
 * and cannot be traversed. Non blocked cells have a traversing cost, that might be
 * different for each cell. The goal is to reach the target cell from a starting cell.
 */
public class MazeGraph implements Graph<Cell> {

    public static final byte BLOCKED_CELL = 0;

    private static final String[] DIR_NAME = {"LEFT", "RIGHT", "UP", "DOWN"};
    private static final int[] DIR_ROW = {0, 0, -1, 1};
    private static final int[] DIR_COL = {-1, 1, 0, 0};

    public final int targetRow;
    public final int targetCol;

    private final byte[][] grid;

    /**
     * Creates a new maze instance from the provided grid, target row and target column.<p>
     *
     * The value of a cell in the grid is the cost of traversing that cell in the maze.
     * Cells with a value less than or equal to zero are blocked and cannot be traversed.
     *
     * @param grid a matrix
     * @param targetRow row of the target cell
     * @param targetCol column of the target cell
     * @return a new {@code MazeGraph} instance
     * @throws NullPointerException if grid is {@code null}
     * @throws IllegalArgumentException if grid is not a valid maze or if the target cell is blocked
     * @throws ArrayIndexOutOfBoundsException if the target cell coordinates are out of range
     */
    public static MazeGraph newInstance(byte[][] grid, int targetRow, int targetCol) {
        if (grid.length == 0 || grid[0].length == 0) {
            throw new IllegalArgumentException("Empty grid");
        }
        if (grid[targetRow][targetCol] <= BLOCKED_CELL) {
            throw new IllegalArgumentException("Target cell is blocked");
        }
        int width = grid.length;
        int height = grid[0].length;
        byte[][] newGrid = new byte[width][height];
        for (int row = 0; row < width; ++row) {
            if (grid[row].length != height) {
                throw new IllegalArgumentException("Invalid grid");
            }
            for (int col = 0; col < height; ++col) {
                int cell = grid[row][col];
                newGrid[row][col] = (byte)(Math.max(cell, BLOCKED_CELL));
            }
        }
        return new MazeGraph(newGrid, targetRow, targetCol);
    }

    MazeGraph(byte[][] grid, int targetRow, int targetCol) {
        this.grid = grid;
        this.targetRow = targetRow;
        this.targetCol = targetCol;
    }

    /**
     * Returns the edges that bring to the cells that can be reached
     * from the provided {@link Cell}.
     *
     * @param cell a {@link Cell} in this maze
     * @return the edges that bring to the cells that can be reached
     * from the provided cell
     * @throws NullPointerException if cell is {@code null}
     */
    @Override
    public List<Edge<Cell>> outgoingEdges(Cell cell) {
        int width = grid.length;
        int height = grid[0].length;
        List<Edge<Cell>> edges = new ArrayList<>(4);
        for (int i = 0; i < DIR_NAME.length; i++) {
            int newRow = cell.row + DIR_ROW[i];
            int newCol = cell.col + DIR_COL[i];
            if (newRow >= 0 && newRow < width && newCol >= 0 && newCol < height) {
                byte cost = grid[newRow][newCol];
                if (cost != BLOCKED_CELL) {
                    Cell newCell = new Cell(newRow, newCol);
                    edges.add(new Edge<>(DIR_NAME[i], cost, newCell));
                }
            }
        }
        return edges;
    }


    /**
     * Returns {@code true} if the provided {@link Cell}
     * is the target cell.
     *
     * @param cell a cell in this maze
     * @return {@code true} if the provided cell is the target cell
     */
    @Override
    public boolean isTarget(Cell cell) {
        return cell.row == this.targetRow &&
                cell.col == this.targetCol;
    }

    /**
     * Returns a new {@code MazeGraph} instance with the same grid
     * and a new target cell.
     *
     * @param newTargetRow the row index of the new target cell
     * @param newTargetCol the column index of the new target cell
     * @return a new {@code MazeGraph} instance
     * @throws IllegalArgumentException if the new target cell is blocked
     * @throws ArrayIndexOutOfBoundsException if the new target cell coordinates are out of range
     */
    public MazeGraph withTargetCell(int newTargetRow, int newTargetCol) {
        if (grid[newTargetRow][newTargetCol] == BLOCKED_CELL) {
            throw new IllegalArgumentException("Target cell is blocked");
        }
        return new MazeGraph(grid, newTargetRow, newTargetCol);
    }

    /**
     * Returns the cost of traversing the cell at the provided coordinates,
     * or zero if the cell is blocked.
     * @param row the row index of a cell
     * @param col the column index of a cell
     * @return the cost of traversing the cell at the provided coordinates, or zero if it is blocked
     * @throws ArrayIndexOutOfBoundsException if the provided cell coordinates are out of range
     */
    public int cell(int row, int col) {
        return grid[row][col];
    }

    /**
     * Returns the width of this maze.
     *
     * @return the width of this maze
     */
    public int width() {
        return this.grid[0].length;
    }

    /**
     * Returns the height of this maze.
     *
     * @return the height of this maze
     */
    public int height() {
        return this.grid.length;
    }

    /**
     * Returns the row index of the target cell.
     *
     * @return the row index of the target cell.
     */
    public int targetRow() {
        return targetRow;
    }

    /**
     * Returns the column index of the target cell.
     *
     * @return the column index of the target cell.
     */
    public int targetCol() {
        return targetCol;
    }
}
