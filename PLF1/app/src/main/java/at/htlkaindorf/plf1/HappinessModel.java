package at.htlkaindorf.plf1;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class HappinessModel {

    private Map<String, List<Integer>> data = new HashMap<>();

    public HappinessModel() {
        List<Integer> values = new ArrayList<>();
        Random rand = new Random();

        values.add(Math.abs(rand.nextInt()%10) +1);
        data.put("Marge", values);
        values = new ArrayList<>();

        values.add(Math.abs(rand.nextInt()%10 +1));
        data.put("Lisa", values);
        values = new ArrayList<>();

        values.add(Math.abs(rand.nextInt()%10 +1));
        data.put("Bart", values);
        values = new ArrayList<>();

        values.add(Math.abs(rand.nextInt()%10 +1));
        data.put("Homer", values);
        values = new ArrayList<>();


        List<String> names = new ArrayList<>();
        names.add("Marge");
        names.add("Lisa");
        names.add("Bart");
        names.add("Homer");
        for (int i = 0; i < 16; i++) {
            int randomIndex = Math.abs(rand.nextInt()%4);
            int randomVal = Math.abs(rand.nextInt()%10 +1);
            addHappinessValue(names.get(randomIndex), randomVal);
        }
    }

    public void addHappinessValue(String name, int value){
        if(!data.containsKey(name)){
            List<Integer> list = new ArrayList<>();
            list.add(value);
            data.put(name, list);
        }
        else{
            List<Integer> list = data.get(name);
            list.add(value);
            data.remove(name);
            data.put(name, list);
        }
    }


    public String getTopThreeString(){
        String toReturn = "";
        Map<Double, List<String>> avgs = new TreeMap<>();
        for(Map.Entry<String, List<Integer>> entry : data.entrySet()){
            List<Integer> vals = entry.getValue();
            int counter = 0;
            double avg = 0;
            for (int i = 0; i < vals.size(); i++) {
                avg += vals.get(i);
                counter++;
            }
            if(counter!=0){
                avg /= counter;
            }
            if(avgs.containsKey(avg)){
                List<String> list = avgs.get(avg);
                list.add(entry.getKey());
                avgs.remove(avg);
                avgs.put(avg, list);
            }
            else{
                List<String> list = new ArrayList<>();
                list.add(entry.getKey());
                avgs.put(avg, list);
            }
        }

        double[] vals = new double[avgs.size()];
        int counter = 0;
        for(Map.Entry<Double, List<String>> entry : avgs.entrySet()){
            vals[counter] = entry.getKey();
            counter++;
            Log.println(Log.ASSERT, "asd", entry.getKey() + " - " + entry.getValue());
        }
        double[] top = new double[3];
        top[0] = vals[vals.length-1];
        top[1] = vals[vals.length-2];
        top[2] = vals[vals.length-3];

        for (int i = 0; i < 3; i++) {
            toReturn += String.format("%.2f - ", top[i]);
            List<String> list = avgs.get(top[i]);
            for (int j = 0; j < list.size(); j++) {
                if(j==list.size()-1){
                    toReturn += list.get(j)+"\n";
                }
                else{
                    toReturn += list.get(j)+",";
                }
            }
        }



        return toReturn;
    }

}
