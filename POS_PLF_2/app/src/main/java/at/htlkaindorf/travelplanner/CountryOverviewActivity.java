package at.htlkaindorf.travelplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import at.htlkaindorf.travelplanner.bl.Trip;

public class CountryOverviewActivity extends AppCompatActivity {

    private SearchView svSearch;
    private TextView tvOverview;
    private List<Trip> list;
    private List<Trip> filtered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_overview);
        svSearch = findViewById(R.id.svCity);
        tvOverview = findViewById(R.id.tvOverview);
        list = new ArrayList<>();
        for (int i = 0; i < getIntent().getIntExtra("size", 0); i++) {
            Trip t = (Trip) getIntent().getSerializableExtra(i+"");
            list.add(t);
        }
        list.sort((t1, t2) -> t1.getStartDate().compareTo(t2.getStartDate()));
        filtered = new ArrayList<>();
        filtered.addAll(list);
        updateOverview();

        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filtered.clear();
                filtered.addAll(list);
                filtered.removeIf(t -> !t.getCity().contains(newText));
                updateOverview();
                return false;
            }
        });
    }
    public void updateOverview(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String text = "Overview:\n";
        for (int i = 0; i < filtered.size(); i++) {
            Trip t = filtered.get(i);
            text += t.getCity()+": "+dtf.format(t.getStartDate())+" - "+dtf.format(t.getStartDate().plusDays(t.getDuration()))+"\n";
        }
        tvOverview.setText(text);
    }
}
