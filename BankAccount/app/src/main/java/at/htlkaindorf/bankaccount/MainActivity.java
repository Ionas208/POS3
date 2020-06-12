package at.htlkaindorf.bankaccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import at.htlkaindorf.bankaccount.bl.AccountAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvRec;
    private AccountAdapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvRec = findViewById(R.id.rvRec);
        ad = new AccountAdapter(this);
        rvRec.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        rvRec.setAdapter(ad);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(ad != null){
            switch(item.getItemId()){
                case R.id.miStudents:
                    ad.filter("Students");
                    break;
                case R.id.miGiro:
                    ad.filter("Giro");
                    break;
                default:
                    ad.filter("All");
                    break;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
