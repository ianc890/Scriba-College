package com.example.scriba.scribacollege.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scriba.scribacollege.R;
import com.example.scriba.scribacollege.config.Config;
import com.example.scriba.scribacollege.model.QuizQuestion;

import java.util.HashMap;
import java.util.Map;

public class QuizQuestionsActivity extends AppCompatActivity {

    public static final String KEY_SUBJECT = "subject";
    public static final String KEY_QUESTION= "question";
    public static final String KEY_OPTION_ONE= "optionOne";
    public static final String KEY_OPTION_TWO= "optionTwo";
    public static final String KEY_OPTION_THREE= "optionThree";
    public static final String KEY_OPTION_FOUR= "optionFour";
    public static final String KEY_ANSWER= "answer";

    private EditText editTextSubject;
    private EditText editTextQuestion;
    private EditText editTextOptionOne, editTextOptionTwo, editTextOptionThree, editTextOptionFour;
    private EditText editTextAnswer;

    private Button buttonCreateQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_questions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        editTextQuestion = (EditText) findViewById(R.id.editTextQuestion);
        editTextOptionOne = (EditText) findViewById(R.id.editTextOptionOne);
        editTextOptionTwo = (EditText) findViewById(R.id.editTextOptionTwo);
        editTextOptionThree = (EditText) findViewById(R.id.editTextOptionThree);
        editTextOptionFour = (EditText) findViewById(R.id.editTextOptionFour);
        editTextAnswer = (EditText) findViewById(R.id.editTextAnswer);

        buttonCreateQuestion = (Button) findViewById(R.id.createQuestion);

        buttonCreateQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == buttonCreateQuestion){
                    createQuizQuestion();
                }
            }
        });
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

    private void clearText(TextView subject, TextView question, TextView optionOne, TextView optionTwo, TextView optionThree, TextView optionFour, TextView answer) {
        subject.setText("");
        question.setText("");
        optionOne.setText("");
        optionTwo.setText("");
        optionThree.setText("");
        optionFour.setText("");
        answer.setText("");
    }

    private void createQuizQuestion(){

        final String subject = editTextSubject.getText().toString();
        final String question = editTextQuestion.getText().toString();
        final String optionOne = editTextOptionOne.getText().toString();
        final String optionTwo = editTextOptionTwo.getText().toString();
        final String optionThree = editTextOptionThree.getText().toString();
        final String optionFour = editTextOptionFour.getText().toString();
        final String answer = editTextAnswer.getText().toString();

        final QuizQuestion quizQuestion = new QuizQuestion(subject, question, optionOne, optionTwo, optionThree, optionFour, answer);

        /*quizQuestion.setSubject(editTextSubject.getText().toString());
        quizQuestion.setQuestion(editTextQuestion.getText().toString());
        quizQuestion.setOptionOne(editTextOptionOne.getText().toString());
        quizQuestion.setOptionTwo(editTextOptionTwo.getText().toString());
        quizQuestion.setOptionThree(editTextOptionThree.getText().toString());
        quizQuestion.setOptionFour(editTextOptionFour.getText().toString());
        quizQuestion.setAnswer(editTextAnswer.getText().toString());
*/
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.INSERT_QUESTION_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(QuizQuestionsActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(QuizQuestionsActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_SUBJECT, quizQuestion.getSubject());
                params.put(KEY_QUESTION, quizQuestion.getQuestion());
                params.put(KEY_OPTION_ONE, quizQuestion.getOptionOne());
                params.put(KEY_OPTION_TWO, quizQuestion.getOptionTwo());
                params.put(KEY_OPTION_THREE, quizQuestion.getOptionThree());
                params.put(KEY_OPTION_FOUR, quizQuestion.getOptionFour());
                params.put(KEY_ANSWER, quizQuestion.getAnswer());
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
