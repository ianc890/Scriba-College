package com.example.scriba.scribacollege.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scriba.scribacollege.R;
import com.example.scriba.scribacollege.config.Config;
import com.example.scriba.scribacollege.model.QuizQuestion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {

    private String myJSON;

    private static final String TAG_RESULTS="result";
    private static final String TAG_SUBJECT = "subject";
    private static final String TAG_QUESTION ="question";
    private static final String TAG_OPTION_ONE ="optionOne";
    private static final String TAG_OPTION_TWO ="optionTwo";
    private static final String TAG_OPTION_THREE ="optionThree";
    private static final String TAG_OPTION_FOUR ="optionFour";
    private static final String TAG_ANSWER = "answer";

    JSONArray jsonFiles = null;

    private TextView scoreTV;
    private TextView questionTV;
    private RadioGroup radioGroup;
    private RadioButton radioOne;
    private RadioButton radioTwo;
    private RadioButton radioThree;
    private RadioButton radioFour;
    private Button nextButton;

    private QuizQuestion quizQuestion;
    private List<QuizQuestion> questionList = new ArrayList<>();
    Map<String,String> questionsMap;
    public int pos = 0;
    public int score = 0;
    public String chosenSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        chosenSubject = intent.getStringExtra("subject_chosen");

        scoreTV = (TextView) findViewById(R.id.score);
        questionTV = (TextView)findViewById(R.id.quiz_question);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioOne = (RadioButton)findViewById(R.id.radio1);
        radioTwo = (RadioButton)findViewById(R.id.radio2);
        radioThree = (RadioButton)findViewById(R.id.radio3);
        radioFour = (RadioButton)findViewById(R.id.radio4);
        nextButton = (Button) findViewById(R.id.next);

        RetrieveJSONData retrieve = new RetrieveJSONData();
        retrieve.execute();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioGroup.getCheckedRadioButtonId() != -1) {
                    int radioSelected = radioGroup.getCheckedRadioButtonId();

                    String answer;
                    String userSelection = getSelectedAnswer(radioSelected);
                    answer = questionList.get(pos).getAnswer();

                    Log.e("ANSWER", answer);
                    Log.e("USER_ANSWER", userSelection);

                    if (userSelection.equals(answer)) {
                        Toast.makeText(QuizActivity.this, "Correct answer", Toast.LENGTH_LONG).show();
                        score++;
                        pos++;

                        scoreTV.setText(String.valueOf(score));

                        if(pos >= questionList.size()) {
                            quizFinish();
                        } else {
                            nextQuestion(pos);
                        }
                    } else {
                        Toast.makeText(QuizActivity.this, "Wrong answer", Toast.LENGTH_LONG).show();
                        pos++;

                        if(pos >= questionList.size()) {
                            quizFinish();
                        } else {
                            nextQuestion(pos);
                        }
                    }

                } else {
                    Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_LONG).show();
                }

                //uncheckRadioButton();

                radioGroup.clearCheck();
            }
        });
    }

    private void nextQuestion(int position) {
        questionTV.setText(questionList.get(position).getQuestion());
        radioOne.setText(questionList.get(position).getOptionOne());
        radioTwo.setText(questionList.get(position).getOptionTwo());
        radioThree.setText(questionList.get(position).getOptionThree());
        radioFour.setText(questionList.get(position).getOptionFour());
    }

    private void quizFinish() {
        //Creating an alert dialog to confirm quiz has finished
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Quiz finished!");
        alertDialogBuilder.setMessage("Your score is "+score);
        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        // start the upload activity
                        Intent intent = new Intent(QuizActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // start the upload activity
                        Intent intent = new Intent(QuizActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                });

        // show the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.quiz_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed(); // closes the current activity and returns to previous activity in the lifecycle
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private String getSelectedAnswer(int radioSelected) {
        String answerSelected = null;

        if(radioSelected == R.id.radio1){
            answerSelected = questionList.get(pos).getOptionOne();
        }

        if(radioSelected == R.id.radio2){
            answerSelected = questionList.get(pos).getOptionTwo();
        }

        if(radioSelected == R.id.radio3){
            answerSelected = questionList.get(pos).getOptionThree();
        }

        if(radioSelected == R.id.radio4){
            answerSelected = questionList.get(pos).getOptionFour();
        }

        return answerSelected;
    }

    private void uncheckRadioButton(){
        radioOne.setChecked(false);
        radioTwo.setChecked(false);
        radioThree.setChecked(false);
        radioFour.setChecked(false);
    }

    protected void showQuestionList(int position){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            jsonFiles = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i = 0; i< jsonFiles.length(); i++){

                String subject = null;
                String question = null;
                String optionOne = null;
                String optionTwo = null;
                String optionThree = null;
                String optionFour = null;
                String answer = null;

                try {
                    JSONObject c = jsonFiles.getJSONObject(i);
                    {

                    }
                    subject = c.getString(TAG_SUBJECT);
                    question = c.getString(TAG_QUESTION);
                    optionOne = c.getString(TAG_OPTION_ONE);
                    optionTwo = c.getString(TAG_OPTION_TWO);
                    optionThree = c.getString(TAG_OPTION_THREE);
                    optionFour = c.getString(TAG_OPTION_FOUR);
                    answer = c.getString(TAG_ANSWER);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                questionsMap = new HashMap<String,String>();

                questionsMap.put(TAG_SUBJECT, subject);
                questionsMap.put(TAG_QUESTION, question);
                questionsMap.put(TAG_OPTION_ONE, optionOne);
                questionsMap.put(TAG_OPTION_TWO, optionTwo);
                questionsMap.put(TAG_OPTION_THREE, optionThree);
                questionsMap.put(TAG_OPTION_FOUR, optionFour);
                questionsMap.put(TAG_ANSWER, answer);

                QuizQuestion quizQuestionDetails = new QuizQuestion(subject, question, optionOne, optionTwo, optionThree, optionFour, answer);
                questionList.add(quizQuestionDetails);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        questionTV.setText(questionList.get(position).getQuestion());
        radioOne.setText(questionList.get(position).getOptionOne());
        radioTwo.setText(questionList.get(position).getOptionTwo());
        radioThree.setText(questionList.get(position).getOptionThree());
        radioFour.setText(questionList.get(position).getOptionFour());
    }

    public void home(MenuItem item) {
        Intent intent = new Intent(QuizActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    class RetrieveJSONData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            InputStream inputStream = null;
            String result = null;
            try {
                URL url = new URL(Config.RETRIEVE_QUESTIONS_URL+"?subject="+chosenSubject);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                inputStream = new BufferedInputStream(con.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                result = sb.toString();
            } catch (Exception e) {

            }
            finally {
                try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result){
            myJSON=result;
            showQuestionList(pos);
        }
    }

}
