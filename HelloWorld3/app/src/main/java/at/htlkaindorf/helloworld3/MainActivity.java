package at.htlkaindorf.helloworld3;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etInput;
    private Button btContinue;
    private TextView tvTextView;
    private TextView tvMessage;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get instance of views
        etInput = (EditText)findViewById(R.id.etEditText);
        btContinue = (Button)findViewById(R.id.btButton);
        tvTextView = (TextView)findViewById(R.id.tvTextView);
        tvMessage = (TextView)findViewById(R.id.tvMessage);

        //create Eventhandler for onClickEvent:
        btContinue.setEnabled(false);
        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                String input = etInput.getText().toString();
                tvMessage.setText(getString(R.string.outputmessage, input));
                btContinue.setText(R.string.finished);
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
                btContinue.setEnabled(s.length() > 0);
            }
        });
    }
}