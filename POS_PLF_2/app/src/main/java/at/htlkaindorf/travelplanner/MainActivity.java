package at.htlkaindorf.travelplanner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import at.htlkaindorf.travelplanner.bl.TripAdapter;

public class MainActivity extends AppCompatActivity {

    public static MainActivity pointer;
    private RecyclerView rvRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pointer = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvRec = findViewById(R.id.rvTrips);

        TripAdapter ad = new TripAdapter();
        rvRec.setLayoutManager(new LinearLayoutManager(this));
        rvRec.setAdapter(ad);
    }
}
