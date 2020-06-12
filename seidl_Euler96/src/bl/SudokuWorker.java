/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.util.concurrent.Callable;

/**
 *
 * @author 10jon
 */
public class SudokuWorker implements Callable<Integer> {

    private Sudoku s;
    
    public SudokuWorker(String sudokuString) {
        s = new Sudoku(sudokuString);
    }

    @Override
    public Integer call() throws Exception {
        s.solve();
        return s.getTopNum();
    }
    
}
