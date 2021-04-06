package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity2 extends AppCompatActivity {
    Integer answerNumber = 0;
    Integer submitNumber = 0;
    Integer correctNumber = 0;
    Integer quizNumber = 0;
    Integer progress = 2;
    String name;
    String[] quizTitle;
    String[] quizDetail;
    String[] quizAnswer1;
    String[] quizAnswer2;
    String[] quizAnswer3;
    TextView questionTitleTextView;
    TextView questionDetailTextView;
    TextView progressTextView;
    Button answer1;
    Button answer2;
    Button answer3;
    Button submitButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);
        DealActivity.getInstance().addActivity(this);
        Intent intentFromSecond = getIntent();
        correctNumber = intentFromSecond.getIntExtra("correctNumber", 0);
        name = intentFromSecond.getStringExtra("name");
        Resources resources = getResources();
        quizTitle = this.getResources().getStringArray(R.array.questionTitle);
        quizDetail = this.getResources().getStringArray(R.array.questionDetail);
        quizAnswer1 = this.getResources().getStringArray(R.array.questionAnswer1);
        quizAnswer2 = this.getResources().getStringArray(R.array.questionAnswer2);
        quizAnswer3 = this.getResources().getStringArray(R.array.questionAnswer3);
        questionTitleTextView = findViewById(R.id.questionTitle);
        questionTitleTextView.setText(quizTitle[quizNumber]);
        questionDetailTextView = findViewById(R.id.questionDetails);
        questionDetailTextView.setText(quizDetail[quizNumber]);
        answer1 = findViewById(R.id.answer1);
        answer1.setText(quizAnswer1[quizNumber]);
        answer2 = findViewById(R.id.answer2);
        answer2.setText(quizAnswer2[quizNumber]);
        answer3 = findViewById(R.id.answer3);
        answer3.setText(quizAnswer3[quizNumber]);
    }

    public void selectAnswer(View view) {
        switch (view.getId()) {
            case R.id.answer1:
                answerNumber = 1;
                break;
            case R.id.answer2:
                answerNumber = 2;
                break;
            case R.id.answer3:
                answerNumber = 3;
                break;
            default:
                answerNumber = 0;
                break;
        }
    }

    public void submitAndNext(View view){
        progressBar = findViewById(R.id.progressBar);
        progressTextView = findViewById(R.id.progressTextView);
        submitButton = findViewById(R.id.nextButton);
        if(quizNumber <= 3){
            if(submitNumber == 0){
                switch (quizNumber){
                    case 0:
                        if (answerNumber == 0) {
                            Toast.makeText(this, "Please choose a answer", Toast.LENGTH_LONG).show();
                        }
                        else if (answerNumber == 1){
                            answer1.setBackgroundResource(R.drawable.button_false_background);
                            answer2.setBackgroundResource(R.drawable.button_correct_background);
                            submitButton.setText("Next");
                            submitNumber++;
                        }
                        else if (answerNumber == 2){
                            answer2.setBackgroundResource(R.drawable.button_correct_background);
                            submitButton.setText("Next");
                            submitNumber++;
                            correctNumber++;
                        }
                        else{
                            answer2.setBackgroundResource(R.drawable.button_correct_background);
                            answer3.setBackgroundResource(R.drawable.button_false_background);
                            submitButton.setText("Next");
                            submitNumber++;
                        }
                        break;
                    case 1:
                    case 3:
                        if (answerNumber == 0) {
                            Toast.makeText(this, "Please choose a answer", Toast.LENGTH_LONG).show();
                        }
                        else if (answerNumber == 1){
                            answer1.setBackgroundResource(R.drawable.button_correct_background);
                            submitButton.setText("Next");
                            submitNumber++;
                            correctNumber++;
                        }
                        else if (answerNumber == 2){
                            answer1.setBackgroundResource(R.drawable.button_correct_background);
                            answer2.setBackgroundResource(R.drawable.button_false_background);
                            submitButton.setText("Next");
                            submitNumber++;
                        }
                        else{
                            answer1.setBackgroundResource(R.drawable.button_correct_background);
                            answer3.setBackgroundResource(R.drawable.button_false_background);
                            submitButton.setText("Next");
                            submitNumber++;
                        }
                        break;
                    case 2:
                        if (answerNumber == 0) {
                            Toast.makeText(this, "Please choose a answer", Toast.LENGTH_LONG).show();
                        }
                        else if (answerNumber == 1) {
                            answer1.setBackgroundResource(R.drawable.button_false_background);
                            answer3.setBackgroundResource(R.drawable.button_correct_background);
                            submitButton.setText("Next");
                            submitNumber++;
                        }
                        else if (answerNumber == 2) {
                            answer2.setBackgroundResource(R.drawable.button_false_background);
                            answer3.setBackgroundResource(R.drawable.button_correct_background);
                            submitButton.setText("Next");
                            submitNumber++;
                        }
                        else if (answerNumber == 3) {
                            answer3.setBackgroundResource(R.drawable.button_correct_background);
                            submitButton.setText("Next");
                            submitNumber++;
                            correctNumber++;
                        }
                        break;
                    default:
                        break;
                }
            }
            else{
                if(progress < 5) {
                    progress++;
                    progressBar.setProgress(progress);
                    progressTextView.setText(progress.toString() + "/5");
                }
                if (quizNumber == 3){
                    quizNumber++;
                }
                if(quizNumber < 3) {
                    quizNumber++;
                    questionTitleTextView.setText(quizTitle[quizNumber]);
                    questionDetailTextView.setText(quizDetail[quizNumber]);
                    answer1.setText(quizAnswer1[quizNumber]);
                    answer1.setBackgroundResource(R.drawable.answer_background);
                    answer2.setText(quizAnswer2[quizNumber]);
                    answer2.setBackgroundResource(R.drawable.answer_background);
                    answer3.setText(quizAnswer3[quizNumber]);
                    answer3.setBackgroundResource(R.drawable.answer_background);
                }
                submitNumber--;
                submitButton.setText("Submit");
            }
        }
        if (quizNumber == 4){
            Intent intentToResult = new Intent(getApplicationContext(), ResultActivity.class);
            intentToResult.putExtra("correctNumber", correctNumber);
            intentToResult.putExtra("name", name);
            startActivity(intentToResult);
        }
    }
}