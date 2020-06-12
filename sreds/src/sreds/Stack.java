/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sreds;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 10jon
 */
public class Stack {
    int[] stack;
    int tos = 0;

    public Stack(int size) {
        stack = new int[size];
    }
    
    
    
    public void push(int val){
        if(!isFull()){
           stack[tos] = val;
            tos++; 
        }
        else{
            throw new RuntimeException("Stack is full");
        }
    }
    
    public int pop(){
        if(!isEmpty()){
           return stack[--tos]; 
        }
        else{
            throw new RuntimeException("Stack is empty");
        }
    }
    
    public boolean isFull(){
        return tos == stack.length;
    }
    
    public boolean isEmpty(){
        return tos == 0;
    }

    @Override
    public String toString() {
        if(isEmpty()){
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < tos; i++) {
            sb.append(stack[i]+",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        return sb.toString();
    }
    
    
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        
        Consumer consumer = new Consumer(stack, 100);
        Producer producer = new Producer(stack, 100);
        
        Thread cT = new Thread(consumer);
        Thread pT = new Thread(producer);
        
        cT.start();
        pT.start();
    }
}
