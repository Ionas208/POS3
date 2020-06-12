/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import bl.Knoten;
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

/**
 *
 * @author 10jon
 */
public class IOHelper {

    public IOHelper() {

    }

    public static List<Knoten> getKnotenList() {
        try {
            List<Knoten> list = new ArrayList<>();
            Path path = Paths.get(System.getProperty("user.dir"), "src", "dal", "graph.txt");
            FileReader fr = new FileReader(path.toFile());
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            Map<String, Knoten> nodes = new HashMap<>();
            Map<String, List<String>> neighbors = new HashMap<>();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\:");
                String name = parts[0];
                Knoten k = new Knoten(name, new ArrayList<>());
                    nodes.put(name, k);
                if (parts.length > 1) {
                    List<String> neighbors1 = Arrays.asList(parts[1].split("\\,"));
                                        neighbors.put(name, neighbors1);
                }
                else{
                    neighbors.put(name, new ArrayList<>());
                }
            }

            for (Map.Entry<String, Knoten> entry : nodes.entrySet()) {
                String name = entry.getKey();
                Knoten k = entry.getValue();
                List<Knoten> knotenList = new ArrayList<>();
                
                List<String> neighbors1 = neighbors.get(name);
                for (String string : neighbors1) {
                    knotenList.add(nodes.get(string));
                }
                k.setNeighbours(knotenList);
                list.add(k);
            }
            return list;

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
