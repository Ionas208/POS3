package at.htlkaindorf.a2048;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button[][] buttons;
    private GameLogic gl;
    private ImageButton btReset;
    private TableLayout layout;
    private GestureDetectorCompat mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons = new Button[4][4];

        buttons[0][0] = findViewById(R.id.bt00);
        buttons[1][0] = findViewById(R.id.bt10);
        buttons[2][0] = findViewById(R.id.bt20);
        buttons[3][0] = findViewById(R.id.bt30);

        buttons[0][1] = findViewById(R.id.bt01);
        buttons[1][1] = findViewById(R.id.bt11);
        buttons[2][1] = findViewById(R.id.bt21);
        buttons[3][1] = findViewById(R.id.bt31);

        buttons[0][2] = findViewById(R.id.bt02);
        buttons[1][2] = findViewById(R.id.bt12);
        buttons[2][2] = findViewById(R.id.bt22);
        buttons[3][2] = findViewById(R.id.bt32);

        buttons[0][3] = findViewById(R.id.bt03);
        buttons[1][3] = findViewById(R.id.bt13);
        buttons[2][3] = findViewById(R.id.bt23);
        buttons[3][3] = findViewById(R.id.bt33);

        layout = findViewById(R.id.layout);

        MyGestureListener mgl = new MyGestureListener();
        mGestureDetector = new GestureDetectorCompat(this, mgl);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j].setOnTouchListener((v, event) -> {
                    return mGestureDetector.onTouchEvent(event);
                });
            }
        }

        btReset = findViewById(R.id.btReset);
        gl = new GameLogic(buttons);

        btReset.setOnClickListener(v -> {
            gl.reset();
        });
    }
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private final int MIN_DIST = 10;
        private final int MAX_DIST = 2000;
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.println(Log.ASSERT, "asdf", "in");
            float deltaX = e1.getX() - e2.getX();
            float deltaY = e1.getY() - e2.getY();
            float deltaXAbs = Math.abs(deltaX);
            float deltaYAbs = Math.abs(deltaY);
            if (deltaXAbs > MIN_DIST && deltaXAbs < MAX_DIST) {
                if (deltaX > 0) {
                    gl.handleCases('L');
                    return true;
                } else {
                    gl.handleCases('R');
                    return true;
                }
            }
            if (deltaYAbs > MIN_DIST && deltaXAbs < MAX_DIST) {
                if (deltaY > 0) {
                    gl.handleCases('U');
                    return true;
                } else {
                    gl.handleCases('D');
                    return true;
                }
            }
            return false;
        }
    }
}
