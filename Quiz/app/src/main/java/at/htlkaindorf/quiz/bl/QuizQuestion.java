package at.htlkaindorf.quiz.bl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizQuestion {

    private String question;
    private List<String> answers;
    private  int correctAnswer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public QuizQuestion(String question, List<String> answers, int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "QuizQuestion{" +
                "question='" + question + '\'' +
                ", answers=" + answers +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
