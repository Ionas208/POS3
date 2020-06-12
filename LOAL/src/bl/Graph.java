package bl;


import dal.IOHelper;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 10jon
 */
public class Graph {
    
    private List<Knoten> knoten;

    public Graph() {
        knoten = IOHelper.getKnotenList();
        
    }
    
    
    public static void main(String[] args) {
        Graph g = new Graph();
        g.printKnoten();
        g.addKnoten("E");
        g.printKnoten();
        g.delKnoten("E");
        g.printKnoten();
        System.out.println(g.existiertKante("A", "B"));
        System.out.println(g.getAlleKnoten());
        System.out.println(g.getAlleNachbarn("B"));
    }
    
    public List<String> getAlleKnoten(){
        List<String> result = new ArrayList<>();
        for (Knoten knoten1 : knoten) {
            result.add(knoten1.getName());
        }
        return result;
    }
    
    public List<String> getAlleNachbarn(String name){
        for (Knoten knoten1 : knoten) {
            if((knoten1.getName()).equals(name)){
                return knoten1.getNeighbours();
            }
        }
        return new ArrayList<>();
    }
    
    public void printKnoten(){
        System.out.println("----------------");
        for (int i = 0; i < knoten.size(); i++) {
            System.out.println(knoten.get(i));
        }
    }
    
    public boolean existiertKnoten(String name){
        for (Knoten knoten1 : knoten) {
            if((knoten1.getName()).equals(name)){
                return true;
            }
        }
        return false;
    }
    
    public void addKnoten(String name){
        knoten.add(new Knoten(name, new ArrayList<>()));
    }
    
    public void delKnoten(String name){
        for (int i = 0; i < knoten.size(); i++) {
            String name1 = knoten.get(i).getName();
            if(name1.equals(name)){
                knoten.remove(knoten.get(i));
            }
            
        }
    }
    
    public boolean existiertKante(String begin, String end){
        for (Knoten knoten1 : knoten) {
            if((knoten1.getName()).equals(begin)){
                for (String neighbor : knoten1.getNeighbours()) {
                    if(neighbor.equals(end)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
