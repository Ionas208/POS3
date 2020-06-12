package at.htlkaindorf.pairp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.time.LocalDate;
import java.time.LocalTime;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DayPlanner dp = new DayPlanner();
        dp.addEntry(LocalDate.of(2019, 10, 19), new Entry(LocalTime.of(8, 30), "Mehr Schule"));
        dp.addEntry(LocalDate.of(2019, 10, 19), new Entry(LocalTime.of(10, 30), "Pause"));
        dp.addEntry(LocalDate.of(2019, 10, 19), new Entry(LocalTime.of(11, 30), "Noch mehr Schule"));
        dp.addEntry(LocalDate.of(2019, 10, 18), new Entry(LocalTime.of(6, 30), "Frühstück"));
        dp.addEntry(LocalDate.of(2019, 10, 18), new Entry(LocalTime.of(7, 30), "Schule"));

        dp.showAllDays();
        dp.showEntriesOfDay(LocalDate.of(2019, 10, 19));
    }
}
