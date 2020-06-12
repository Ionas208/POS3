package at.htlkaindorf.travelplanner.bl;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.List;

import at.htlkaindorf.travelplanner.CountryOverviewActivity;
import at.htlkaindorf.travelplanner.MainActivity;

public class TripHolder extends RecyclerView.ViewHolder {
    private TextView tvCountry;
    private TextView tvNumberOfTrips;
    private List<Trip> trips;

    public TripHolder(@NonNull View itemView, TextView tvCountry, TextView tvNumberOfTrips) {
        super(itemView);
        this.tvCountry = tvCountry;
        this.tvNumberOfTrips = tvNumberOfTrips;

        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.pointer.getApplicationContext(), CountryOverviewActivity.class);
            Integer size = trips.size();
            intent.putExtra("size", size);
            for (int i = 0; i < trips.size(); i++) {
                intent.putExtra(i+"", trips.get(i));
            }
            MainActivity.pointer.startActivity(intent);
        });
    }

    public TextView getTvCountry() {
        return tvCountry;
    }

    public void setTvCountry(TextView tvCountry) {
        this.tvCountry = tvCountry;
    }

    public TextView getTvNumberOfTrips() {
        return tvNumberOfTrips;
    }

    public void setTvNumberOfTrips(TextView tvNumberOfTrips) {
        this.tvNumberOfTrips = tvNumberOfTrips;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
