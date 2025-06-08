package eth.epieffe.jwalker.example.nqueens;

class Util {

    private Util() {}

    public static String toPrettyString(NQueens nQueens) {
        String hSep = '+' + "---+".repeat(nQueens.size()) + '\n';
        StringBuilder sb = new StringBuilder();
        sb.append(hSep);
        for (int row = nQueens.size() - 1; row >= 0; --row) {
            sb.append('|');
            for (int col = 0; col < nQueens.size(); ++col) {
                if (row == nQueens.row(col)) {
                    sb.append(" X |");
                } else {
                    sb.append("   |");
                }
            }
            sb.append('\n').append(hSep);
        }
        return sb.toString();
    }
}
