/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problem39;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
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
public class TriangleLauncher {
    public void performTriangleCalculation(){
        long startTime = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(12);
        CompletionService<Set<Triple>> service = new ExecutorCompletionService<Set<Triple>>(pool);
        for (int p = 10; p <= 1000; p++) {
            service.submit(new TriangleWorker(p));
        }
        pool.shutdown();
        Set<Triple> maxTriple = null;
        while(!pool.isTerminated()){
            try {
                Future<Set<Triple>> future = service.take();
                Set<Triple> result = future.get();
                if(maxTriple == null || result.size()>maxTriple.size()){
                    maxTriple = result;
                }
            } catch (InterruptedException ex) {
                ex.toString();
            } catch (ExecutionException ex) {
                ex.toString();
            }
        }
        long time = System.currentTimeMillis() - startTime;
        System.out.println("Time "+time);
        System.out.println(new ArrayList<>(maxTriple).get(0).getP());
    }
   
}
