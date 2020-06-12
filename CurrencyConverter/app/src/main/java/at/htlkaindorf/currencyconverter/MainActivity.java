package at.htlkaindorf.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView tvTitle;
    private TextView tvLeft;
    private ImageButton btSwap;
    private TextView tvRight;
    private EditText etInput;
    private TextView tvOutput;
    private String state = "euro";
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvLeft = (TextView)findViewById(R.id.tvLeft);
        btSwap = (ImageButton)findViewById(R.id.btSwap);
        tvRight = (TextView)findViewById(R.id.tvRight);
        etInput = (EditText) findViewById(R.id.etInput);
        tvOutput = (TextView)findViewById(R.id.tvOutput);

        btSwap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state.equals("euro")){
                    tvLeft.setText(R.string.dollar);
                    tvRight.setText(R.string.euro);
                    etInput.setHint(R.string.dollarPreText);
                    tvOutput.setText(R.string.dollarPreOutText);
                    state = "dollar";
                }
                else{
                    tvLeft.setText(R.string.euro);
                    tvRight.setText(R.string.dollar);
                    etInput.setHint(R.string.euroPreText);
                    tvOutput.setText(R.string.euroPreOutText);
                    state = "euro";
                }


            }
        });

        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    if(state=="euro"){
                        double euro = Double.parseDouble(etInput.getText().toString());
                        double dollar = euro * 1.1;
                        tvOutput.setText(String.format("%.2f€ are %.2f$", euro, dollar));
                    }
                    else{
                        double dollar = Double.parseDouble(etInput.getText().toString());
                        double euro = dollar * 0.91;
                        tvOutput.setText(String.format("%.2f$ are %.2f€", dollar, euro));
                    }
                }
                else{
                    if(state=="euro"){
                        tvOutput.setText(R.string.euroPreOutText);
                    }
                    else{
                        tvOutput.setText(R.string.dollarPreOutText);
                    }
                }

            }
        });

    }
}
