package com.lappungdev.relationshipmeter.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lappungdev.relationshipmeter.R;
import com.lappungdev.relationshipmeter.adapter.DbHelper;
import com.lappungdev.relationshipmeter.model.Question;

import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    TextView tvQuestion, tvNumber;
    EditText etAnswer;
    Button btSubmit;
    ProgressBar pbTime;
    CountDownTimer mCountDownTimer;
    int i = 100;
    private List<Question> questionList;
    private int quid = 0;
    private Question currentQuestion;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        DbHelper dbHelper = new DbHelper(this);
        questionList = dbHelper.getAllQuestions();
        currentQuestion = questionList.get(quid);

        setQuestionView();

        btSubmit = findViewById(R.id.btSubmit);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionNext();
            }
        });
    }

    public void countDown() {
        i = 100;
        pbTime = findViewById(R.id.pbTime);
        mCountDownTimer = new CountDownTimer(10000, 100) {

            @Override
            public void onTick(long millisUntilFinished) {
                i--;
                pbTime.setProgress(i);
            }

            @Override
            public void onFinish() {
                pbTime.setProgress(0);
                btSubmit = findViewById(R.id.btSubmit);
                btSubmit.callOnClick();
            }
        };
        mCountDownTimer.start();
    }

    private void setQuestionView() {
        tvQuestion = findViewById(R.id.tvQuestion);
        tvNumber = findViewById(R.id.tvNumber);

        tvQuestion.setText(currentQuestion.getQuestion());
        tvNumber.setText("Pertanyaan " + (quid + 1) + "/" + questionList.size());
        quid++;

        countDown();
    }

    public void questionNext() {
        etAnswer = findViewById(R.id.etAnswer);
        String answerStr = etAnswer.getText().toString().toLowerCase();
        if (checkAnswer(answerStr)) {
            score++;
        }
        etAnswer.setText("");

        if (quid < 4) {
            currentQuestion = questionList.get(quid);
            setQuestionView();
        }/*Intent intent = new Intent(this, HasilActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score);
            intent.putExtras(b);
            startActivity(intent);
            finish();*/
    }

    public boolean checkAnswer(String answer) {
        return true;
    }
}
