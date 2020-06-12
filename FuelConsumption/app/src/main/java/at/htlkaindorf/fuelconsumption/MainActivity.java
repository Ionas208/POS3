package at.htlkaindorf.fuelconsumption;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText fuelInLiter;
    private EditText distanceInKm;
    private Button calc;
    private TextView output;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fuelInLiter = (EditText)findViewById(R.id.etFuelInLiter);
        distanceInKm = (EditText)findViewById(R.id.etDistanceInKm);
        calc = (Button)findViewById(R.id.btCalc);
        output = (TextView)findViewById(R.id.tvOutput);

        calc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onCalc(v);
            }
        });
    }

    private void onCalc(View v){
        double liters = Double.parseDouble(fuelInLiter.getText().toString());
        int km = Integer.parseInt(distanceInKm.getText().toString());

        String consumption = String.format("%.2f", liters/km*100);

        output.setText("Consumption: "+consumption);
    }
}
