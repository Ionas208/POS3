/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sreds;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 10jon
 */
public class Producer implements Runnable{
    
    private Stack stack;
    private int cnt;
    public static final Random rand = new Random();

    public Producer(Stack stack, int cnt) {
        this.stack = stack;
        this.cnt = cnt;
    }

    @Override
    public void run() {
        System.out.println("Producer started");
        for (int i = 0; i < cnt; i++) {
            synchronized(stack){
                while(stack.isFull()){
                    System.out.println("Stack is full");
                    try {
                        System.out.println("Producer waiting");
                        stack.wait();
                        System.out.println("Producer finished waiting");
                    } catch (InterruptedException ex) {
                    }
                }
                int pushVal = rand.nextInt(100);
                System.out.println("Producer pushing "+pushVal);
                stack.push(pushVal);
                stack.notify();
                System.out.println(stack);
            }
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException ex) {
            }
        }
    }
    
    
    
}
