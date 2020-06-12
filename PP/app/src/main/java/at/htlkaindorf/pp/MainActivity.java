package at.htlkaindorf.pp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPass;
    private Button btLogin;

    private ImageView ivCheck1;
    private ImageView ivCheck2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        btLogin = findViewById(R.id.btLogin);
        ivCheck1 = findViewById(R.id.ivCheck1);
        ivCheck2 = findViewById(R.id.ivCheck2);

        btLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onSign(v);
            }
        });

    }

    public void onSign(View v){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        if(email.equals("seijod17@htl-kaindorf.at") && pass.equals("123")){
            ivCheck1.setVisibility(View.VISIBLE);
            ivCheck2.setVisibility(View.VISIBLE);
        }
        else{
            ivCheck1.setVisibility(View.INVISIBLE);
            ivCheck2.setVisibility(View.INVISIBLE);
        }
    }
}
