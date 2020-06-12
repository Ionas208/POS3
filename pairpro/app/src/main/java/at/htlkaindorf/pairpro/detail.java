package at.htlkaindorf.pairpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.TestChild1;
import com.example.test.TestChild2;
import com.example.test.TestObject;

public class detail extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        TestObject test = (TestObject) this.getIntent().getSerializableExtra("test");
        tv1.setText(test.getName());
        if(test instanceof TestChild1){
            tv2.setText(((TestChild1) test).getUrl().toString());
        }
        else{
            tv2.setText(((TestChild2)test).getKeyboard());
        }
    }
}
