package bl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 10jon
 */
public class Knoten {
    private String name;
    private List<Knoten> neighbours;

    public Knoten(String name, List<Knoten> neighbours) {
        this.name = name;
        this.neighbours = neighbours;
    }

    public List<String> getNeighbours() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < neighbours.size(); i++) {
            result.add(neighbours.get(i).name);
        }
        return result;
    }

    public void setNeighbours(List<Knoten> neighbours) {
        this.neighbours = neighbours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String result = name + " - ";
        for (int i = 0; i < neighbours.size(); i++) {
            result += neighbours.get(i).name;
        }
        return result;
    }

    
    
    
    
}
