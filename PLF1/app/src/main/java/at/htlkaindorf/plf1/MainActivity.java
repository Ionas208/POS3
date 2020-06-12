package at.htlkaindorf.plf1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SeekBar sbSeek;
    private TextView tvSmiley;
    private EditText etName;
    private Button btSend;
    private TextView tvCurrent;
    private TextView tvOut;

    private HappinessModel hm = new HappinessModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbSeek = findViewById(R.id.sbSeek);
        tvSmiley = findViewById(R.id.tvSmiley);
        etName = findViewById(R.id.etName);
        tvCurrent = findViewById(R.id.tvCurrent);
        btSend = findViewById(R.id.btSend);
        tvOut = findViewById(R.id.tvOut);

        class MySeekListener implements SeekBar.OnSeekBarChangeListener{

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress >= 1 && progress <= 3){
                    tvSmiley.setText("Unhappy Smiley");
                    tvCurrent.setText("Happiness: " + progress);
                }
                else if(progress >=4 && progress <=7){
                    tvSmiley.setText("Neutral Smiley");
                    tvCurrent.setText("Happiness: " + progress);
                }
                else if(progress >=8 && progress <=10){
                    tvSmiley.setText("Happy Smiley");
                    tvCurrent.setText("Happiness: " + progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        }
        sbSeek.setOnSeekBarChangeListener(new MySeekListener());
        btSend.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onSend(v);
            }
        });
    }

    public void onSend(View v){
        String name = etName.getText().toString();
        int value =  sbSeek.getProgress();
        if(name.equals("")){
            Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_LONG).show();
        }
        else{
            hm.addHappinessValue(name, value);
            tvOut.setText(hm.getTopThreeString());
        }
    }
}
