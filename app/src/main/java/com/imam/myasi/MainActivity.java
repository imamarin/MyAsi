package com.imam.myasi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView txtView;
    private List<Question> questionList;
    private int questionCounter = 0;
    private int questionTotal;
    private Question currentQuestion;
    SharedPreferences sh;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = findViewById(R.id.textView1);

        sh = getSharedPreferences("sessionku", MODE_APPEND);
        txtView.setText(sh.getString("idDiagnosa",""));
//        DbHelper dbHelper = new DbHelper(this);
//        questionList =  dbHelper.getAllQuestions();
//
//        questionTotal = questionList.size();
//
//        showNextQuestion();

    }

    private void showNextQuestion() {
        currentQuestion = questionList.get(questionCounter);

        if(questionCounter < questionTotal){
            txtView.setText(currentQuestion.getQuestion());
        }
    }
}