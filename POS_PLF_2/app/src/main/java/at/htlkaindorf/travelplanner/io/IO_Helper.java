package at.htlkaindorf.travelplanner.io;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import at.htlkaindorf.travelplanner.MainActivity;
import at.htlkaindorf.travelplanner.bl.Trip;

public class IO_Helper {
    public static Map<String, List<Trip>> loadTrips(){
        AssetManager am = MainActivity.pointer.getAssets();
        InputStream is = null;
        try {
            is = am.open("travel_data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Trip> list = new BufferedReader(new InputStreamReader(is))
                                            .lines()
                                            .skip(4)
                                            .map(Trip::new)
                                            .collect(Collectors.toList());

        Map<String, List<Trip>> map = new HashMap<>();
        for (Trip t: list) {
            if(map.containsKey(t.getCountry())){
                List<Trip> trips = map.get(t.getCountry());
                trips.add(t);
                map.remove(t.getCountry());
                map.put(t.getCountry(), trips);
            }
            else{
                List<Trip> trips = new ArrayList<>();
                trips.add(t);
                map.put(t.getCountry(), trips);
            }
        }

        return map;
    }
}
