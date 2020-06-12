/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sreds;

import java.util.Random;

/**
 *
 * @author 10jon
 */
public class Consumer implements Runnable{

    private Stack stack;
    private int cnt;
    public static final Random rand = new Random();

    public Consumer(Stack stack, int cnt) {
        this.stack = stack;
        this.cnt = cnt;
    }

    @Override
    public void run() {
        System.out.println("Consumer started");
        for (int i = 0; i < cnt; i++) {
            synchronized(stack){
                while(stack.isEmpty()){
                    System.out.println("Stack is empty");
                    try {
                        System.out.println("Consumer waiting");
                        stack.wait();
                        System.out.println("Consumer finished waiting");
                    } catch (InterruptedException ex) {
                    }
                }
                System.out.println("Consumer popped "+stack.pop());
                System.out.println(stack);
                stack.notify();
            }
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException ex) {
            }
        }
    }
    
}
