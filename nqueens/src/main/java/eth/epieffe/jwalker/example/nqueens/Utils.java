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
package eth.epieffe.jwalker.example.nqueens;

class Utils {

    private Utils() {}

    public static void prettyPrint(NQueens nQueens) {
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
        System.out.println(sb);
    }
}
