package at.htlkaindorf.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import at.htlkaindorf.minesweeper.bl.GameLogic;

public class MainActivity extends AppCompatActivity {

    private Button btEZ;
    private Button btMed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btEZ = findViewById(R.id.btEz);
        btMed = findViewById(R.id.btMed);

        btEZ.setOnClickListener(v -> {
            Intent intent = new Intent(this, game.class);
            intent.putExtra("size", 5);
            intent.putExtra("bombs", 6);
            startActivity(intent);
        });
        btMed.setOnClickListener(v -> {
            Intent intent = new Intent(this, game.class);
            intent.putExtra("size", 9);
            intent.putExtra("bombs", 10);
            startActivity(intent);
        });
    }
}
