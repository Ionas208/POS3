/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sreds;

import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 *
 * @author 10jon
 */
public class NameThread implements Runnable {

    private StringBuilder sb = new StringBuilder(10_000_000);
    
    @Override
    public void run() {
        for (int i = 0; i < 100_000; i++) {
            synchronized(sb){
                sb.append(Thread.currentThread().getName());
            }
        }
    }
    public static void main(String[] args) {
        NameThread nt = new NameThread();
        Thread t1 = new Thread(nt, "FRANZ");
        Thread t2 = new Thread(nt, "hans");
        Thread t3 = new Thread(nt, "LMBAO");
        
        long startTime = System.currentTimeMillis();
        
        t1.start();
        t2.start();
        t3.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException ex) {
            
        }
        long time = System.currentTimeMillis()-startTime;
        System.out.println(time+"");
        System.out.println(nt.getSb().length()+"");
    }

    public StringBuilder getSb() {
        return sb;
    }
    
    
}
