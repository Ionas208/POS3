package at.htlkaindorf.pethome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    public static MainActivity thisPointer;

    private ImageButton btCat;
    private ImageButton btDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        thisPointer = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCat = findViewById(R.id.btCat);
        btDog = findViewById(R.id.btDog);

        btCat.setOnClickListener(v -> {
            Intent intent = new Intent(this, pet_list.class);
            intent.putExtra("isCat", true);
            startActivity(intent);
        });
        btDog.setOnClickListener(v -> {
            Intent intent = new Intent(this, pet_list.class);
            intent.putExtra("isCat", false);
            startActivity(intent);
        });
    }
}
