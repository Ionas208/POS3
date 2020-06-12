package at.htlkaindorf.pethome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.List;

import at.htlkaindorf.pethome.bl.Pet;
import at.htlkaindorf.pethome.bl.PetAdapter;

public class pet_list extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PetAdapter adapter;
    private TextView tvTitle;
    private SearchView tvSearch;
    public static pet_list thisPointer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        thisPointer = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);
        tvTitle = findViewById(R.id.tvHeadline);
        recyclerView = findViewById(R.id.rvLOl);
        tvSearch = findViewById(R.id.tvSearch);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PetAdapter();
        recyclerView.setAdapter(adapter);
        boolean isCat = this.getIntent().getBooleanExtra("isCat", false);
        adapter.filter(isCat);
        if(isCat){
            tvTitle.setText("Cat List");
        }
        else{
            tvTitle.setText("Dog List");
        }
        tvSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(isCat);
                adapter.search(newText);
                return false;
            }
        });
    }
}
