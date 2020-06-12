package at.htlkaindorf.dev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btLOL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLOL = findViewById(R.id.btLOL);

        btLOL.setOnClickListener(v -> onYeet(v));

    }

    private void onYeet(View v){

    }
}
package at.htlkaindorf.dev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btLOL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLOL = findViewById(R.id.btLOL);

        btLOL.setOnClickListener(v -> onYeet(v));

    }

    private void onYeet(View v){

    }
}
