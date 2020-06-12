/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problem39;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 *
 * @author 10jon
 */
public class TriangleWorker implements Callable<Set<Triple>>{
    private int p;

    @Override
    public Set<Triple> call() throws Exception {
        Set<Triple> triples = new HashSet<>();
        System.out.println("doing something with "+p);
        for (int i = 0; i < p/2; i++) {
            for (int j = 0; j < p/2; j++) {
                for (int k = 0; k < p/2; k++) {
                    if(i+j+k==p && i*i + j*j == k*k){
                        triples.add(new Triple(p, i, j, k));
                    }
                }
            }
        } 
        return triples;
    }

    public TriangleWorker(int p) {
        this.p = p;
    }
}
