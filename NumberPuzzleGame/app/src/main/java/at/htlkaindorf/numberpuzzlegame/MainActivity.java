package at.htlkaindorf.numberpuzzlegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.Touch;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button[][] buttons = new Button[4][4];
    private Button btReset;
    private int emptyX;
    private int emptyY;
    private View currentButton;

    private GestureDetectorCompat gest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btReset = findViewById(R.id.btReset);

        buttons[0][0] = findViewById(R.id.bt00);
        buttons[0][1] = findViewById(R.id.bt01);
        buttons[0][2] = findViewById(R.id.bt02);
        buttons[0][3] = findViewById(R.id.bt03);

        buttons[1][0] = findViewById(R.id.bt10);
        buttons[1][1] = findViewById(R.id.bt11);
        buttons[1][2] = findViewById(R.id.bt12);
        buttons[1][3] = findViewById(R.id.bt13);

        buttons[2][0] = findViewById(R.id.bt20);
        buttons[2][1] = findViewById(R.id.bt21);
        buttons[2][2] = findViewById(R.id.bt22);
        buttons[2][3] = findViewById(R.id.bt23);

        buttons[3][0] = findViewById(R.id.bt30);
        buttons[3][1] = findViewById(R.id.bt31);
        buttons[3][2] = findViewById(R.id.bt32);
        buttons[3][3] = findViewById(R.id.bt33);

        class MyGestureListener extends GestureDetector.SimpleOnGestureListener{

            public static final int MIN_DIST = 100;
            public static final int MAX_DIST = 1000;

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float deltaX = e1.getX() - e2.getX();
                float deltaY = e1.getY() - e2.getY();
                float deltaxAbs = Math.abs(deltaX);
                float deltayAbs = Math.abs(deltaY);

                int direction = -1;

                if(deltaxAbs > MIN_DIST && deltaxAbs < MAX_DIST){
                    if(deltaX > 0){
                        direction = 0;
                    }
                    else{
                        direction = 2;
                    }
                }

                if(deltayAbs > MIN_DIST && deltayAbs < MAX_DIST){
                    if(deltaY > 0){
                        direction = 1;
                    }
                    else{
                        direction = 3;
                    }
                }
                int x = -1;
                int y = -1;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if(buttons[i][j].getId() == currentButton.getId()){
                            y = i;
                            x = j;
                        }
                    }
                }
                if(isAllowedToMove(x, y, direction)){
                    swap(x, y);
                }
                if(isFinished()){
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            buttons[i][j].setEnabled(false);
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Congratulations! You won!", Toast.LENGTH_LONG).show();
                }


                return true;
            }
        }

        class MyOnTouchListener implements View.OnTouchListener{

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                currentButton = v;
                return gest.onTouchEvent(event);
            }
        }

        MyGestureListener mgl = new MyGestureListener();
        gest = new GestureDetectorCompat(this, mgl);

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initButtons();
            }
        });
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j].setOnTouchListener(new MyOnTouchListener());
            }
        }
        initButtons();
    }

    private void initButtons(){
        List<Integer> numbers = new ArrayList<>();

        int counter = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                numbers.add(counter);
                buttons[i][j].setEnabled(true);
                counter++;
            }
        }
        Collections.shuffle(numbers);
        counter = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String text = numbers.get(counter)+"";
                if(numbers.get(counter) == 0){
                    buttons[i][j].setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                }
                else if(numbers.get(counter)%2==0){
                    buttons[i][j].setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                }
                else{
                    buttons[i][j].setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                }
                if(text.equals("0")){
                    text = "";
                    emptyX = j;
                    emptyY = i;
                }
                buttons[i][j].setText(text);
                counter++;
            }
        }
    }

    private boolean isAllowedToMove(int x, int y, int direction){
        switch(direction){
            case 0:
                try{
                    if(buttons[y][x-1].getText().toString().equals("")){
                        return true;
                    }
                }
                catch(IndexOutOfBoundsException e){

                }
                break;
            case 1:
                try{
                    if(buttons[y-1][x].getText().toString().equals("")){
                        return true;
                    }
                }
                catch(IndexOutOfBoundsException e){

                }
                break;
            case 2:
                try{
                    if(buttons[y][x+1].getText().toString().equals("")){
                        return true;
                    }
                }
                catch(IndexOutOfBoundsException e){

                }
                break;
            case 3:
                try{
                    if(buttons[y+1][x].getText().toString().equals("")){
                        return true;
                    }
                }
                catch(IndexOutOfBoundsException e){

                }
                break;
        }
        return false;
    }

    private boolean isFinished(){
        int counter = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int currentButton;
                try{
                    currentButton = Integer.parseInt(buttons[i][j].getText().toString());
                }
                catch(NumberFormatException e){
                    currentButton = 0;
                }

                if(currentButton == counter){
                    counter++;
                }
                else if(currentButton == 0 && i==3 && j==3){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }

    private void swap(int x, int y){
        String temp = buttons[y][x].getText().toString();
        buttons[y][x].setText(buttons[emptyY][emptyX].getText().toString());
        buttons[emptyY][emptyX].setText(temp);

        ColorStateList temp2 = buttons[y][x].getBackgroundTintList();
        buttons[y][x].setBackgroundTintList(buttons[emptyY][emptyX].getBackgroundTintList());
        buttons[emptyY][emptyX].setBackgroundTintList(temp2);

        emptyY = y;
        emptyX = x;
    }
}
