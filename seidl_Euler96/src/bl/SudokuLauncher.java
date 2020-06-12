/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 10jon
 */
public class SudokuLauncher {
    
    public String[] sudokus;
    
    public SudokuLauncher(){
        String path = System.getProperty("user.dir")+File.separator+"src"+File.separator+"bl"+File.separator+"sudoku.txt";
        FileReader fr = null;
        try {
            fr = new FileReader(path);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        BufferedReader br = new BufferedReader(fr);
        String erg = "";
        String line;
        try {
            while((line = br.readLine()) != null){
                erg += line;
            }
        } catch (IOException ex) {
        }
        String[] tokens = erg.split("Grid [0-9][0-9]");
        sudokus = new String[tokens.length-1]; 
        for (int i = 1; i < tokens.length; i++) {
            sudokus[i-1] = tokens[i];
        }
    }
    
    public void runSudokuCalculation(){
        ExecutorService pool = Executors.newFixedThreadPool(6);
        List<SudokuWorker> workers = new ArrayList<>();
        for (int i = 0; i < sudokus.length; i++) {
            workers.add(new SudokuWorker(sudokus[i]));
        }
        
        List<Future<Integer>> results = null;
        try {
            results = pool.invokeAll(workers);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int sum = 0;
        for (Future<Integer> result : results) {
            try {
                int num = result.get();
                sum += num;
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }
        }
        pool.shutdown();
        System.out.println("Sum: "+sum);
        
    }
    
    public static void main(String[] args) {
        SudokuLauncher s = new SudokuLauncher();
        s.runSudokuCalculation();
    }
}
