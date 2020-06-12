/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 *
 * @author 10jon
 */
public class Sudoku {

    private int[][] field;

    public Sudoku(String text) {
        text = text.replace("\n", "");
        field = new int[9][9];
        int counter = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                field[j][i] = Integer.parseInt(text.charAt(counter) + "");
                counter++;
            }
        }
    }

    @Override
    public String toString() {
        String erg = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                erg += field[j][i];
            }
            erg += "\n";
        }
        return erg;
    }

    public boolean solve() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (field[i][j] == 0) {
                    for (int n = 1; n <= 9; n++) {
                        if (isNumPossible(n, i, j)) {
                            field[i][j] = n;
                            if (solve()) {
                                return true;
                            } else {
                                field[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isNumPossible(int n, int r, int c) {
        //3by3
        int initialR = r - r % 3;
        int initialC = c - c % 3;
        for (int i = initialR; i < initialR + 3; i++) {
            for (int j = initialC; j < initialC + 3; j++) {
                if (field[i][j] == n) {
                    return false;
                }
            }
        }

        //row
        for (int i = 0; i < 9; i++) {
            if (field[r][i] == n) {
                return false;
            }
        }

        //col
        for (int i = 0; i < 9; i++) {
            if (field[i][c] == n) {
                return false;
            }
        }
        return true;
    }

    public int getTopNum() {
        String numString = field[0][0] + "" + field[1][0] + "" + field[2][0];
        int num = Integer.parseInt(numString);
        return num;
    }

}
