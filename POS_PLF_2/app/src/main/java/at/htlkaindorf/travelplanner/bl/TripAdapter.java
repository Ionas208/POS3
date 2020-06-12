package at.htlkaindorf.travelplanner.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import at.htlkaindorf.travelplanner.R;
import at.htlkaindorf.travelplanner.io.IO_Helper;

public class TripAdapter extends RecyclerView.Adapter<TripHolder> {

    private List<String> trips;
    private Map<String, List<Trip>> map;

    public TripAdapter(){
        map = IO_Helper.loadTrips();
        trips = new ArrayList<>();
        for (Map.Entry<String, List<Trip>> entry : map.entrySet()) {
            trips.add(entry.getKey());
        }
        Collections.sort(trips);
    }

    @NonNull
    @Override
    public TripHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item,parent,false);
        TextView tvCountry = view.findViewById(R.id.tvCountry);
        TextView tvNumberOfTrips = view.findViewById(R.id.tvNumberOfTrips);
        return new TripHolder(view, tvCountry, tvNumberOfTrips);
    }

    @Override
    public void onBindViewHolder(@NonNull TripHolder holder, int position) {
        String country = trips.get(position);
        List<Trip> list = map.get(country);
        holder.getTvCountry().setText(country + "("+list.get(0).getCountryCode()+")");
        holder.setTrips(list);
        int numTrips = list.size();
        List<LocalDate> dates = list.stream()
                .map(Trip::getStartDate)
                .collect(Collectors.toList());
        List<LocalDate> maxDates = list.stream()
                .map(Trip::getStartDatePlusDuration)
                .collect(Collectors.toList());
        dates.sort((d1, d2) -> d1.compareTo(d2));
        maxDates.sort((d1, d2) -> d1.compareTo(d2));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate minDate = dates.get(0);
        LocalDate maxDate = maxDates.get(maxDates.size()-1);
        int between = (int) ChronoUnit.DAYS.between(minDate, maxDate);

        holder.getTvNumberOfTrips().setText(numTrips + " Trips - "+ between + " days ("+dtf.format(minDate)+" - "+dtf.format(maxDate)+")");
        for (LocalDate d:
             dates) {
            System.out.println(d.toString());
        }
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }
}
