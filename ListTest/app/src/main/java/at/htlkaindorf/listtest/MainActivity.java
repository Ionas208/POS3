package at.htlkaindorf.listtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import at.htlkaindorf.listtest.bl.MovieAdapter;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        recyclerView.setAdapter(new MovieAdapter());
    }
}
