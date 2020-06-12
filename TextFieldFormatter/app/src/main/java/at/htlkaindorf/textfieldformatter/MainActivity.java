package at.htlkaindorf.textfieldformatter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

            public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, CheckBox.OnCheckedChangeListener {

    private TextView tvSizeNum;
    private CheckBox cbBold;
    private CheckBox cbItalic;
    private EditText mlText;
    private RadioGroup rbGroup;
    private SeekBar sbFont;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSizeNum = (TextView)findViewById(R.id.tvSizeNum);
        cbBold = (CheckBox) findViewById(R.id.cbBold);
        cbItalic = (CheckBox) findViewById(R.id.cbItalic);
        mlText = (EditText) findViewById(R.id.mlText);
        rbGroup = (RadioGroup) findViewById(R.id.rbGroup);
        sbFont = (SeekBar) findViewById(R.id.sbFont);


        rbGroup.setOnCheckedChangeListener(this);
        sbFont.setOnSeekBarChangeListener(this);
        cbBold.setOnCheckedChangeListener(this);
        cbItalic.setOnCheckedChangeListener(this);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case R.id.rbUbuntu:
                Typeface font;
                font = ResourcesCompat.getFont(getApplicationContext(),R.font.ubuntu_light);
                mlText.setTypeface(font);
                break;
            case R.id.rbComfortaa:
                Typeface font1;
                font1 = ResourcesCompat.getFont(getApplicationContext(),R.font.comfortaa_light);
                mlText.setTypeface(font1);
                break;
            case R.id.rbRoboto:
                Typeface font2;
                font2 = ResourcesCompat.getFont(getApplicationContext(),R.font.roboto_mono_thin);
                mlText.setTypeface(font2);
                break;
        }
    }

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    mlText.setTextSize(TypedValue.COMPLEX_UNIT_SP, progress);
                    tvSizeNum.setText(progress+"");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    switch(buttonView.getId()){
                        case R.id.cbBold:
                            Typeface font;
                            if(!isChecked){
                                font = Typeface.defaultFromStyle(Typeface.NORMAL);
                            }
                            else{
                                font = Typeface.defaultFromStyle(Typeface.BOLD);
                            }
                            mlText.setTypeface(font);
                            break;
                        case R.id.cbItalic:
                            Typeface font1;
                            if(!isChecked){
                                font1 = Typeface.defaultFromStyle(Typeface.NORMAL);
                            }
                            else{
                                font1 = Typeface.defaultFromStyle(Typeface.ITALIC);
                            }
                            mlText.setTypeface(font1);
                            break;
                    }
                }
            }
