package at.htlkaindorf.minesweeper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
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

import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import at.htlkaindorf.minesweeper.bl.GameLogic;

public class game extends AppCompatActivity {

    private GridLayout gridLayout;
    private Button[][] buttons;
    private GameLogic gl;
    private ImageButton btReset;
    private boolean begin = true;
    public static game thisPointer;
    private boolean running = true;
    private TextView tvTime;
    private Timer timer;
    private int flagCount;
    private TextView tvFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        thisPointer = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gl);
        btReset = findViewById(R.id.btReset);
        tvTime = findViewById(R.id.tvTime);
        tvFlag = findViewById(R.id.tvFlagNum);

        startTimer();

        int size = this.getIntent().getIntExtra("size", 0);
        int bombs = this.getIntent().getIntExtra("bombs", 0);
        gridLayout.setRowCount(size);
        gridLayout.setColumnCount(size);
        flagCount = bombs;
        tvFlag.setText(flagCount+"");

        GridLayout.LayoutParams llp = new GridLayout.LayoutParams(gridLayout.getLayoutParams());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        buttons = new Button[size][size];
        llp.setMargins(5,5,5,5);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Button bt = new Button(this);
                bt.setText("");
                bt.setId(i*10 + j);
                bt.setTypeface(bt.getTypeface(), Typeface.BOLD);
                bt.setTextSize(22);
                bt.setBackgroundColor(0xFF989898);

                gridLayout.addView(bt);
                ViewGroup.LayoutParams params = bt.getLayoutParams();
                GridLayout.LayoutParams lp = (GridLayout.LayoutParams)bt.getLayoutParams();
                lp.setMargins(4,4,4,4);
                params.width = width / size -2*4;
                params.height = width / size -2*4;
                buttons[i][j] = bt;
            }
        }
        gl = new GameLogic(buttons, this, bombs, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j].setOnClickListener(new buttonClickListener());
                buttons[i][j].setOnLongClickListener(new buttonLongClickListener());
            }
        }

        btReset.setOnClickListener(v -> {
            begin = true;
            running = true;
            flagCount = bombs;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    buttons[i][j].setText("");
                    //buttons[i][j].setBackgroundColor(0xFFD3D3D3);
                    buttons[i][j].setBackgroundColor(0xFF989898);
                }
            }
            try {
                timer.cancel();
            }
            catch(NullPointerException e){

            }
            startTimer();
        });
    }

    private class buttonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(running){
                if(begin){
                    gl.placeMines(v.getId());
                    begin = false;
                }
                else{
                    gl.makeMove(v.getId());
                }
            }
        }
    }

    private class buttonLongClickListener implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View v) {
                Drawable asd = getApplicationContext().getResources().getDrawable(R.drawable.flag);
                if(gl.flag(v.getId())){
                    flagCount--;
                    if(flagCount<10){
                        tvFlag.setText(""+flagCount);
                    }
                    else{
                        tvFlag.setText(" "+flagCount);
                    }

                    v.setBackground(asd);
                }
                else{
                    flagCount++;
                    if(flagCount<10){
                        tvFlag.setText(""+flagCount);
                    }
                    else{
                        tvFlag.setText(" "+flagCount);
                    }
                    v.setBackground(createBackground());
                    //v.setBackgroundColor(0xFFD3D3D3);
                    v.setBackgroundColor(0xFF989898);
                }

            return true;
        }
    }

    public Drawable createBackground(){
        return new Button(this).getBackground();
    }

    public void endGame(boolean won){
        running = false;
        if(won){
            showAlertDialogButtonClicked(gridLayout, true);
        }
        else{
            showAlertDialogButtonClicked(gridLayout, false);
        }

    }

    public void startTimer(){
        final int[] seconds={0};
        seconds[0]=0;
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seconds[0]++;
                //System.out.println(seconds[0]);
                int minutes = seconds[0]/60;
                int second = seconds[0]%60;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvTime.setText(String.format("%02d:%02d",minutes,second));
                    }
                });

            }
        },0,1000);
    }

    public void showAlertDialogButtonClicked(View view, boolean won) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(won){
            builder.setTitle("You Won!");
            builder.setMessage("Congratultions");
        }
        else{
            builder.setTitle("You Lost!");
            builder.setMessage("What a pity...");
        }

        builder.setPositiveButton("Continue", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
