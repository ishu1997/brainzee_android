package com.example.shubham.brainzee;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    int locationofcorrect;
    int incorrect;
    int score = 0;
    int numberofQues = 1;
    String timer_value;
    String max_questions;
    int countdown;



    ArrayList<Integer> answers = new ArrayList<Integer>();

    LinearLayout Quizresult;
    LinearLayout Quizinfo;
    LinearLayout Playground;

    EditText Timervalue;
    EditText Maxquestions;
    Button play_btn;

    TextView scoreTextview;
    TextView numberTextView;
    TextView Timertextview;
    TextView Percentage_show;


    public void Play(View view) {
        timer_value =Timervalue.getText().toString();
       max_questions =Maxquestions.getText().toString();



        if (timer_value.matches("") || max_questions.matches("")) {
            Toast.makeText(this, "fields cant be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        else{


            Quizresult.setVisibility(View.GONE);
            Quizinfo.setVisibility(View.GONE);
            Playground.setVisibility(View.VISIBLE);



            numberofQues = Integer.parseInt(max_questions);
            scoreTextview.setText(Integer.toString(score) + "/" + max_questions);
            numberTextView.setText(Integer.toString(numberofQues));
            Timertextview.setText(timer_value + "s");
            countdown = Integer.parseInt(timer_value);

            generateQues();


// -------------------- setting up counter---------
            new CountDownTimer((countdown*1000)+100 , 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    Timertextview.setText(String.valueOf(millisUntilFinished/1000)+ "s");
                    if(numberofQues<=0){
                       // Percentage_show.setText(String.valueOf(score + "/" + max_questions));
                        Percentage_show.setText(Integer.toString(percent()) +"%");
                        Quizresult.setVisibility(View.VISIBLE);
                        Quizinfo.setVisibility(View.GONE);
                        Playground.setVisibility(View.GONE);
                        cancel();

                    }
                }

                @Override
                public void onFinish() {
                    if(numberofQues!=0 ) {
                        Percentage_show.setText(Integer.toString(percent())+"%");

                        //  Percentage_show.setText(String.valueOf(score + "/" + max_questions));
                        Quizresult.setVisibility(View.VISIBLE);
                        Quizinfo.setVisibility(View.GONE);
                        Playground.setVisibility(View.GONE);
                    }

                }
            }.start();

        }

    }



    public void generateQues() {

        TextView sumTextview = findViewById(R.id.sumTextView);
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);


        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextview.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationofcorrect = rand.nextInt(4);

        for (int i = 0; i < 4; i++) {

            if (i == locationofcorrect) {
                answers.add(a + b);
            } else {

                incorrect = rand.nextInt(42);

                while (incorrect == (a + b)) {
                    incorrect = rand.nextInt(42);
                }


                answers.add(incorrect);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

        answers.clear();
    }


    public void chooseAnswer(View view) {



        if (view.getTag().toString().equals(Integer.toString(locationofcorrect))) {
            //Log.i("CORRECT","correct");
            score++;
            scoreTextview.setText(Integer.toString(score) + "/" + max_questions);
            Toast.makeText(MainActivity.this, "Correct!!!", Toast.LENGTH_SHORT).show();
        } else {
            //Log.i("inCORRECT", "incorrect");

            Toast.makeText(MainActivity.this,"Incorrect(-_-)", Toast.LENGTH_SHORT).show();
        }
        numberofQues--;
        numberTextView.setText(Integer.toString(numberofQues));
        generateQues();
    }

    public int  percent(){
       float out = Float.parseFloat(max_questions);
        float ans = (score/out)*100;
        return ((int)ans);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


   //     generateQues();

        Quizinfo  = findViewById(R.id.QuizInfo);
        Quizresult  = findViewById(R.id.QuizResult);
        Playground  = findViewById(R.id.playground);
        Timervalue = findViewById(R.id.timerValue);
        Maxquestions = findViewById(R.id.MaxQuestions);
        scoreTextview = findViewById(R.id.scoreTextView);
        numberTextView = findViewById(R.id.numberTextView);
        Timertextview = findViewById(R.id.timerTextView);
        Percentage_show = findViewById(R.id.percentage_show);


        Quizresult.setVisibility(View.GONE);
        Quizinfo.setVisibility(View.VISIBLE);
        Playground.setVisibility(View.GONE);


    }


    public void Replay(View view) {

        countdown = 0;
        score = 0;

        Quizresult.setVisibility(View.GONE);
        Playground.setVisibility(View.GONE);
        Quizinfo.setVisibility(View.VISIBLE);

    }
}
