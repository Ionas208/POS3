package at.htlkaindorf.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TableLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.htlkaindorf.quiz.bl.QuizQuestion;

public class MainActivity extends AppCompatActivity {

    private Button btQuestion;

    private Button[] progressButtons = new Button[5];
    private Button[] ansButtons = new Button[4];

    private Button btContinue;

    private Button btCatMemes;
    private Button btCatComm;

    private TextView tvCategory;
    private ImageButton btHamburger;

    private TableLayout layoutEverything;
    private LinearLayout layoutCategory;

    private boolean inCategoryMode = true;

    private int currentQuestion = 0;
    private int score = 0;
    private int maxScore;
    public Map<Integer, QuizQuestion> questions = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btQuestion = (Button) findViewById(R.id.btQuestion);

        tvCategory = findViewById(R.id.tvCategory);
        btHamburger = findViewById(R.id.btHamburger);

        ansButtons[0] = findViewById(R.id.btAns1);
        ansButtons[1] = findViewById(R.id.btAns2);
        ansButtons[2] = findViewById(R.id.btAns3);
        ansButtons[3] = findViewById(R.id.btAns4);

        btCatMemes = findViewById(R.id.btCatMemes);
        btCatComm = findViewById(R.id.btCatComm);

        progressButtons[0] = findViewById(R.id.btProg1);
        progressButtons[1] = findViewById(R.id.btProg2);
        progressButtons[2] = findViewById(R.id.btProg3);
        progressButtons[3] = findViewById(R.id.btProg4);
        progressButtons[4] = findViewById(R.id.btProg5);

        layoutCategory = findViewById(R.id.layoutCategory);
        layoutEverything = findViewById(R.id.layoutEverything);

        btContinue = findViewById(R.id.btContinue);

        class AnswerListener implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                int button = v.getId();
                int clicked = -1;
                for (int i = 0; i < 4; i++) {
                    if (button == ansButtons[i].getId()) {
                        clicked = i;
                    }
                }
                int correctAnswer = questions.get(currentQuestion - 1).getCorrectAnswer();
                Button correctButton = ansButtons[correctAnswer];

                if (correctAnswer == clicked) {
                    v.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    progressButtons[currentQuestion - 1].setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    score++;
                } else {
                    v.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    progressButtons[currentQuestion - 1].setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    correctButton.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                }

                for (int i = 0; i < 4; i++) {
                    ansButtons[i].setEnabled(false);
                }
                btContinue.setEnabled(true);
            }
        }

        class CategoryButtonsOnClickListener implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btCatMemes:
                        try {
                            init("Memes");
                        } catch (IOException e) {
                            Toast.makeText(getApplicationContext(), "Questions not found!", Toast.LENGTH_LONG).show();
                        }
                        tvCategory.setText("Category: Memes");
                        toDefault();
                        break;
                    case R.id.btCatComm:
                        try {
                            init("General Knowledge");
                        } catch (IOException e) {
                            Toast.makeText(getApplicationContext(), "Questions not found!", Toast.LENGTH_LONG).show();
                        }
                        tvCategory.setText("Category: General Knowledge");
                        toDefault();
                        break;
                }
                switchCategory();
            }
        }
        for (int i = 0; i < 4; i++) {
            ansButtons[i].setOnClickListener(new AnswerListener());
        }
        btCatComm.setOnClickListener(new CategoryButtonsOnClickListener());
        btCatMemes.setOnClickListener(new CategoryButtonsOnClickListener());
        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueListener(v);
            }
        });
    }

    public void toggleCategoryChooser(View v) {
        switchCategory();
    }

    private void switchCategory() {
        if (inCategoryMode) {
            layoutEverything.setVisibility(View.VISIBLE);
            btQuestion.setVisibility(View.VISIBLE);
            layoutCategory.setVisibility(View.GONE);
            btHamburger.setEnabled(true);
            inCategoryMode = false;
        } else {
            layoutEverything.setVisibility(View.GONE);
            btQuestion.setVisibility(View.GONE);
            layoutCategory.setVisibility(View.VISIBLE);
            tvCategory.setText("Choose a category");
            btHamburger.setEnabled(false);
            inCategoryMode = true;
        }
    }

    private void toDefault() {
        for (int i = 0; i < ansButtons.length; i++) {
            ansButtons[i].setText("");
            ansButtons[i].setEnabled(false);
            ansButtons[i].setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
        }
        for (int i = 0; i < progressButtons.length; i++) {
            progressButtons[i].setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
        }
        currentQuestion = 0;
        score = 0;
        btQuestion.setText("CLICK \"START\" TO START");
        btContinue.setText("START");
        btContinue.setEnabled(true);
    }

    private void init(String category) throws IOException {
        questions.clear();
        InputStream file = null;
        switch (category) {
            case "Memes":
                file = getResources().openRawResource(R.raw.qmemes);
                break;
            case "General Knowledge":
                file = getResources().openRawResource(R.raw.qgen);
                break;
        }
        InputStreamReader isr = new InputStreamReader(file);
        BufferedReader br = new BufferedReader(isr);

        String line;
        int i = 0;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\;");
            String question = parts[0];
            int correctAnswer = Integer.parseInt(parts[1]);
            List<String> answers = new ArrayList<>();
            answers.add(parts[2]);
            answers.add(parts[3]);
            answers.add(parts[4]);
            answers.add(parts[5]);

            QuizQuestion q = new QuizQuestion(question, answers, correctAnswer);
            questions.put(i, q);
            i++;
        }
        maxScore = questions.size();
    }

    private void continueListener(View v) {
        for (int i = 0; i < 4; i++) {
            ansButtons[i].setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
        }

        if (currentQuestion == 0) {
            btContinue.setText("Continue");
            for (int i = 0; i < 5; i++) {
                progressButtons[i].setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
            }
        }

        if (currentQuestion < maxScore) {
            btQuestion.setText((questions.get(currentQuestion)).getQuestion());
            List<String> ans = (questions.get(currentQuestion)).getAnswers();
            for (int i = 0; i < 4; i++) {
                ansButtons[i].setText(ans.get(i));
                ansButtons[i].setEnabled(true);
            }
            btContinue.setEnabled(false);
            currentQuestion++;
        } else {
            btQuestion.setText("You reached " + score + "/" + maxScore);
            btContinue.setText("Restart");

            for (int i = 0; i < 4; i++) {
                ansButtons[i].setText("");
            }
            score = 0;
            currentQuestion = 0;
        }
    }
}