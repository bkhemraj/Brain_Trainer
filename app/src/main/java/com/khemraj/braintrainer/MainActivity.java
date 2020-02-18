package com.khemraj.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView timer;
    TextView result;
    TextView question;
    Random rand;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    Button playagain;
    int locationCorrectAnswer;
    TextView score;
    int scoreCounter = 0;
    int totalQuestionsCounter = 0;
    ConstraintLayout constraintLayout;

   ArrayList<Integer> answers = new ArrayList<Integer>();

   public void playAgain(View view){

       option1.setEnabled(true);
       option2.setEnabled(true);
       option3.setEnabled(true);
       option4.setEnabled(true);
       playagain.setVisibility(View.INVISIBLE);
       result.setVisibility(View.INVISIBLE);
       timer.setText("30s");
       scoreCounter = 0;
       totalQuestionsCounter = 0;
       score.setText(Integer.toString(scoreCounter) + "/" + Integer.toString(totalQuestionsCounter));

       newQuestion();

       new CountDownTimer(30100,1000){

           @Override
           public void onTick(long millisUntilFinished) {
               timer.setText( Integer.toString((int) (millisUntilFinished)/1000)+"s");
           }

           @Override
           public void onFinish() {
               result.setText("Done!");
               playagain.setVisibility(View.VISIBLE);
               option1.setEnabled(false);
               option2.setEnabled(false);
               option3.setEnabled(false);
               option4.setEnabled(false);

           }
       }.start();
   }

   public void newQuestion(){
       rand = new Random();
       int a,b;
       a = rand.nextInt(101);
       b = rand.nextInt(100);
       question.setText(Integer.toString(a)+ " + " + Integer.toString(b));

       answers.clear();
       locationCorrectAnswer = rand.nextInt(4);
       for(int i=0;i<4;i++){
           if(i==locationCorrectAnswer){
               answers.add(a+b);
           }else {
               int wrong = rand.nextInt(205);
               while(wrong == a+b){
                   wrong = rand.nextInt(205);
               }
               answers.add(wrong);
           }
       }

       option1.setText(Integer.toString(answers.get(0)));
       option2.setText(Integer.toString(answers.get(1)));
       option3.setText(Integer.toString(answers.get(2)));
       option4.setText(Integer.toString(answers.get(3)));
   }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = (ConstraintLayout)findViewById(R.id.constraintLayout);
        score = findViewById(R.id.score);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        playagain = findViewById(R.id.buttonPlayAgain);
        result = findViewById(R.id.result);

        button = findViewById(R.id.goButton);
        button.setVisibility(View.VISIBLE);
        timer = findViewById(R.id.timer);
        question = findViewById(R.id.question);



    }

    public void start(View view){
        button.setVisibility(View.INVISIBLE);
        constraintLayout.setVisibility(View.VISIBLE);
        playAgain (playagain);

    }

    public void checkAnswer(View view){
       result.setVisibility(View.VISIBLE);
        if((view.getTag().toString()).equals(Integer.toString(locationCorrectAnswer))){
            result.setText("Correct!");
            scoreCounter++;
            totalQuestionsCounter++;
            score.setText(Integer.toString(scoreCounter) + "/" + Integer.toString(totalQuestionsCounter));


        }else {
            result.setText("Wrong :(");
            totalQuestionsCounter++;
            score.setText(Integer.toString(scoreCounter) + "/" + Integer.toString(totalQuestionsCounter));
        }
        newQuestion();
    }

}
