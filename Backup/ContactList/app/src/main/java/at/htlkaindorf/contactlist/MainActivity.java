package at.htlkaindorf.contactlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SearchView;

import at.htlkaindorf.contactlist.bl.ContactAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvRec;
    private SearchView svSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rvRec = findViewById(R.id.rvRec);
        svSearch = findViewById(R.id.svSearch);

        rvRec.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        ContactAdapter ad = new ContactAdapter(this);
        rvRec.setAdapter(ad);


        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ad.filter(newText);
                return true;
            }
        });
    }


}
