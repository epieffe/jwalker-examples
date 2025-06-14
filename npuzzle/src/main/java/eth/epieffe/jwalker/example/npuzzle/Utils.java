package eth.epieffe.jwalker.example.npuzzle;

import java.util.Random;

class Utils {

    private static final Random random = new Random();

    public static NPuzzle newRandomNPuzzle(int size) {
        if (size > Byte.MAX_VALUE) {
            throw new IllegalArgumentException("Size is too large");
        }
        byte[] table = new byte[size * size];
        for (int i = 0; i < table.length; ++i) {
            table[i] = (byte) i;
        }
        shuffle(table);
        int emptyIndex = -1;
        for (int i = 0; i < table.length; ++i) {
            if (table[i] == NPuzzle.EMPTY_CELL) {
                emptyIndex = i;
                break;
            }
        }
        return new NPuzzle((byte) size, (byte) emptyIndex, table);
    }

    public static NPuzzle newNPuzzle(int... numbers) {
        int size = (int) Math.sqrt(numbers.length);
        if (size == 0 || size * size != numbers.length) {
            throw new IllegalArgumentException("Invalid size");
        }
        if (size > Byte.MAX_VALUE) {
            throw new IllegalArgumentException("Size is too large");
        }
        // Find empty cell index
        int emptyIndex = -1;
        for (int i = 0; i < numbers.length; ++i) {
            if (numbers[i] <= NPuzzle.EMPTY_CELL) {
                emptyIndex = i;
                break;
            }
        }
        if (emptyIndex == -1) {
            throw new IllegalArgumentException("Missing empty cell");
        }
        // Copy nums in a new byte array
        byte[] table = new byte[numbers.length];
        for (int i = 0; i < numbers.length; ++i) {
            table[i] = (byte) numbers[i];
        }
        table[emptyIndex] = NPuzzle.EMPTY_CELL;
        // Each number from 0 to table.length must be present
        boolean[] found = new boolean[table.length];
        for (byte b : table) {
            if (b < 0 || b >= table.length) {
                throw new IllegalArgumentException("Invalid number: " + b);
            }
            found[b] = true;
        }
        for (int i = 0; i < table.length; ++i) {
            if (!found[i]) {
                throw new IllegalArgumentException("Missing number: " + i);
            }
        }

        return new NPuzzle((byte) size, (byte) emptyIndex, table);
    }

    public static boolean isSolvable(NPuzzle nPuzzle) {
        int inversions = countInversions(nPuzzle.table);
        if (nPuzzle.size % 2 == 0) {
            return (inversions + nPuzzle.emptyRow()) % 2 != 0;
        } else {
            return inversions % 2 == 0;
        }
    }

    public static void prettyPrint(NPuzzle nPuzzle) {
        StringBuilder hSep = new StringBuilder().append('+');
        int digitLength = String.valueOf(nPuzzle.table.length).length();
        for (int i = 0; i < nPuzzle.size; ++i) {
            hSep.append("-".repeat(digitLength + 2));
            hSep.append('+');
        }
        hSep.append('\n');

        String numFormat = " %" + digitLength + "d ";
        StringBuilder sb = new StringBuilder().append(hSep);
        for (int row = 0; row < nPuzzle.size; ++row) {
            sb.append('|');
            for (int col = 0; col < nPuzzle.size; ++col) {
                byte cell = nPuzzle.cell(row, col);
                if (cell != NPuzzle.EMPTY_CELL) {
                    sb.append(String.format(numFormat, cell));
                } else {
                    sb.append(" ".repeat(digitLength + 2));
                }
                sb.append('|');
            }
            sb.append('\n').append(hSep);
        }
        System.out.println(sb);
    }

    private static int countInversions(byte[] numbers) {
        int inversions = 0;
        for (int i = 0; i < numbers.length; ++i) {
            for (int j = i + 1; j < numbers.length; ++j) {
                if (numbers[i] == NPuzzle.EMPTY_CELL || numbers[j] == NPuzzle.EMPTY_CELL) {
                    continue;
                }
                if (numbers[i] > numbers[j]) {
                    ++inversions;
                }
            }
        }
        return inversions;
    }

    private static void shuffle(byte[] array) {
        for (int i = array.length - 1; i > 0; --i) {
            int j = random.nextInt(i + 1);
            byte temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
