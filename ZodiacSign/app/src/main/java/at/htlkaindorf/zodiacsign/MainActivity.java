package at.htlkaindorf.zodiacsign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import at.htlkaindorf.zodiacsign.bl.ZodiacAdapter;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rc = findViewById(R.id.rcList);

        rc.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        rc.setAdapter(new ZodiacAdapter());
    }
}
