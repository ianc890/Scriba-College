package com.example.scriba.scribacollege.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scriba.scribacollege.R;
import com.example.scriba.scribacollege.config.Config;
import com.example.scriba.scribacollege.model.StudyPlan;

import java.util.HashMap;
import java.util.Map;

public class CreateStudyPlanActivity extends AppCompatActivity {

    public static final String KEY_TIME_ONE = "time_one";
    public static final String KEY_TIME_TWO = "time_two";
    public static final String KEY_TIME_THREE = "time_three";
    public static final String KEY_TIME_FOUR = "time_four";
    public static final String KEY_TIME_FIVE = "time_five";

    public static final String KEY_MONDAY_ONE = "monday_one";
    public static final String KEY_MONDAY_TWO = "monday_two";
    public static final String KEY_MONDAY_THREE = "monday_three";
    public static final String KEY_MONDAY_FOUR = "monday_four";
    public static final String KEY_MONDAY_FIVE = "monday_five";

    public static final String KEY_TUESDAY_ONE = "tuesday_one";
    public static final String KEY_TUESDAY_TWO = "tuesday_two";
    public static final String KEY_TUESDAY_THREE = "tuesday_three";
    public static final String KEY_TUESDAY_FOUR = "tuesday_four";
    public static final String KEY_TUESDAY_FIVE = "tuesday_five";

    public static final String KEY_WEDNESDAY_ONE = "wednesday_one";
    public static final String KEY_WEDNESDAY_TWO = "wednesday_two";
    public static final String KEY_WEDNESDAY_THREE = "wednesday_three";
    public static final String KEY_WEDNESDAY_FOUR = "wednesday_four";
    public static final String KEY_WEDNESDAY_FIVE = "wednesday_five";

    public static final String KEY_THURSDAY_ONE = "thursday_one";
    public static final String KEY_THURSDAY_TWO = "thursday_two";
    public static final String KEY_THURSDAY_THREE = "thursday_three";
    public static final String KEY_THURSDAY_FOUR = "thursday_four";
    public static final String KEY_THURSDAY_FIVE = "thursday_five";

    public static final String KEY_FRIDAY_ONE = "friday_one";
    public static final String KEY_FRIDAY_TWO = "friday_two";
    public static final String KEY_FRIDAY_THREE = "friday_three";
    public static final String KEY_FRIDAY_FOUR = "friday_four";
    public static final String KEY_FRIDAY_FIVE = "friday_five";

    public static final String KEY_SATURDAY_ONE = "saturday_one";
    public static final String KEY_SATURDAY_TWO = "saturday_two";
    public static final String KEY_SATURDAY_THREE = "saturday_three";
    public static final String KEY_SATURDAY_FOUR = "saturday_four";
    public static final String KEY_SATURDAY_FIVE = "saturday_five";

    public static final String KEY_SUNDAY_ONE = "sunday_one";
    public static final String KEY_SUNDAY_TWO = "sunday_two";
    public static final String KEY_SUNDAY_THREE = "sunday_three";
    public static final String KEY_SUNDAY_FOUR = "sunday_four";
    public static final String KEY_SUNDAY_FIVE = "sunday_five";

    private EditText editTextTimeOne, editTextTimeTwo, editTextTimeThree, editTextTimeFour, editTextTimeFive;
    private EditText editTextMondayOne, editTextMondayTwo, editTextMondayThree, editTextMondayFour, editTextMondayFive;
    private EditText editTextTuesdayOne, editTextTuesdayTwo, editTextTuesdayThree, editTextTuesdayFour, editTextTuesdayFive;
    private EditText editTextWednesdayOne, editTextWednesdayTwo, editTextWednesdayThree, editTextWednesdayFour, editTextWednesdayFive;
    private EditText editTextThursdayOne, editTextThursdayTwo, editTextThursdayThree, editTextThursdayFour, editTextThursdayFive;
    private EditText editTextFridayOne, editTextFridayTwo, editTextFridayThree, editTextFridayFour, editTextFridayFive;
    private EditText editTextSaturdayOne, editTextSaturdayTwo, editTextSaturdayThree, editTextSaturdayFour, editTextSaturdayFive;
    private EditText editTextSundayOne, editTextSundayTwo, editTextSundayThree, editTextSundayFour, editTextSundayFive;

    private TableLayout tableLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_study_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextTimeOne = (EditText) findViewById(R.id.editTextTimeOne);
        editTextTimeTwo = (EditText) findViewById(R.id.editTextTimeTwo);
        editTextTimeThree = (EditText) findViewById(R.id.editTextTimeThree);
        editTextTimeFour = (EditText) findViewById(R.id.editTextTimeFour);
        editTextTimeFive = (EditText) findViewById(R.id.editTextTimeFive);

        editTextMondayOne = (EditText) findViewById(R.id.editTextMondayOne);
        editTextMondayTwo = (EditText) findViewById(R.id.editTextMondayTwo);
        editTextMondayThree = (EditText) findViewById(R.id.editTextMondayThree);
        editTextMondayFour = (EditText) findViewById(R.id.editTextMondayFour);
        editTextMondayFive = (EditText) findViewById(R.id.editTextMondayFive);

        editTextTuesdayOne = (EditText) findViewById(R.id.editTextTuesdayOne);
        editTextTuesdayTwo = (EditText) findViewById(R.id.editTextTuesdayTwo);
        editTextTuesdayThree = (EditText) findViewById(R.id.editTextTuesdayThree);
        editTextTuesdayFour = (EditText) findViewById(R.id.editTextTuesdayFour);
        editTextTuesdayFive = (EditText) findViewById(R.id.editTextTuesdayFive);

        editTextWednesdayOne = (EditText) findViewById(R.id.editTextWednesdayOne);
        editTextWednesdayTwo = (EditText) findViewById(R.id.editTextWednesdayTwo);
        editTextWednesdayThree = (EditText) findViewById(R.id.editTextWednesdayThree);
        editTextWednesdayFour = (EditText) findViewById(R.id.editTextWednesdayFour);
        editTextWednesdayFive = (EditText) findViewById(R.id.editTextWednesdayFive);

        editTextThursdayOne = (EditText) findViewById(R.id.editTextThursdayOne);
        editTextThursdayTwo = (EditText) findViewById(R.id.editTextThursdayTwo);
        editTextThursdayThree = (EditText) findViewById(R.id.editTextThursdayThree);
        editTextThursdayFour = (EditText) findViewById(R.id.editTextThursdayFour);
        editTextThursdayFive = (EditText) findViewById(R.id.editTextThursdayFive);

        editTextFridayOne = (EditText) findViewById(R.id.editTextFridayOne);
        editTextFridayTwo = (EditText) findViewById(R.id.editTextFridayTwo);
        editTextFridayThree = (EditText) findViewById(R.id.editTextFridayThree);
        editTextFridayFour = (EditText) findViewById(R.id.editTextFridayFour);
        editTextFridayFive = (EditText) findViewById(R.id.editTextFridayFive);

        editTextSaturdayOne = (EditText) findViewById(R.id.editTextSaturdayOne);
        editTextSaturdayTwo = (EditText) findViewById(R.id.editTextSaturdayTwo);
        editTextSaturdayThree = (EditText) findViewById(R.id.editTextSaturdayThree);
        editTextSaturdayFour = (EditText) findViewById(R.id.editTextSaturdayFour);
        editTextSaturdayFive = (EditText) findViewById(R.id.editTextSaturdayFive);

        editTextSundayOne = (EditText) findViewById(R.id.editTextSundayOne);
        editTextSundayTwo = (EditText) findViewById(R.id.editTextSundayTwo);
        editTextSundayThree = (EditText) findViewById(R.id.editTextSundayThree);
        editTextSundayFour = (EditText) findViewById(R.id.editTextSundayFour);
        editTextSundayFive = (EditText) findViewById(R.id.editTextSundayFive);

        tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createStudyPlan();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.study_plan_menu, menu);
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

    public void zoomIn(MenuItem item) {
        // calculate current scale x and y value of ImageView
        float x = tableLayout.getScaleX();
        float y = tableLayout.getScaleY();
        // set increased value of scale x and y to perform zoom in functionality
        tableLayout.setScaleX((float) (x + 1));
        tableLayout.setScaleY((float) (y + 1));
    }


    public void zoomOut(MenuItem item) {
        // calculate current scale x and y value of ImageView
        float x = tableLayout.getScaleX();
        float y = tableLayout.getScaleY();
        // set decreased value of scale x and y to perform zoom out functionality
        tableLayout.setScaleX((float) (x - 1));
        tableLayout.setScaleY((float) (y - 1));
    }

    private void createStudyPlan() {
        final String timeOne = editTextTimeOne.getText().toString();
        final String timeTwo = editTextTimeTwo.getText().toString();
        final String timeThree = editTextTimeThree.getText().toString();
        final String timeFour = editTextTimeFour.getText().toString();
        final String timeFive = editTextTimeFive.getText().toString();

        final String mondayOne = editTextMondayOne.getText().toString();
        final String mondayTwo = editTextMondayTwo.getText().toString();
        final String mondayThree = editTextMondayThree.getText().toString();
        final String mondayFour = editTextMondayFour.getText().toString();
        final String mondayFive = editTextMondayFive.getText().toString();

        final String tuesdayOne = editTextTuesdayOne.getText().toString();
        final String tuesdayTwo = editTextTuesdayTwo.getText().toString();
        final String tuesdayThree = editTextTuesdayThree.getText().toString();
        final String tuesdayFour = editTextTuesdayFour.getText().toString();
        final String tuesdayFive = editTextTuesdayFive.getText().toString();

        final String wednesdayOne = editTextWednesdayOne.getText().toString();
        final String wednesdayTwo = editTextWednesdayTwo.getText().toString();
        final String wednesdayThree = editTextWednesdayThree.getText().toString();
        final String wednesdayFour = editTextWednesdayFour.getText().toString();
        final String wednesdayFive = editTextWednesdayFive.getText().toString();

        final String thursdayOne = editTextThursdayOne.getText().toString();
        final String thursdayTwo = editTextThursdayTwo.getText().toString();
        final String thursdayThree = editTextThursdayThree.getText().toString();
        final String thursdayFour = editTextThursdayFour.getText().toString();
        final String thursdayFive = editTextThursdayFive.getText().toString();

        final String fridayOne = editTextFridayOne.getText().toString();
        final String fridayTwo = editTextFridayTwo.getText().toString();
        final String fridayThree = editTextFridayThree.getText().toString();
        final String fridayFour = editTextFridayFour.getText().toString();
        final String fridayFive = editTextFridayFive.getText().toString();

        final String saturdayOne = editTextSaturdayOne.getText().toString();
        final String saturdayTwo = editTextSaturdayTwo.getText().toString();
        final String saturdayThree = editTextSaturdayThree.getText().toString();
        final String saturdayFour = editTextSaturdayFour.getText().toString();
        final String saturdayFive = editTextSaturdayFive.getText().toString();

        final String sundayOne = editTextSundayOne.getText().toString();
        final String sundayTwo = editTextSundayTwo.getText().toString();
        final String sundayThree = editTextSundayThree.getText().toString();
        final String sundayFour = editTextSundayFour.getText().toString();
        final String sundayFive = editTextSundayFive.getText().toString();

        final StudyPlan studyPlan = new StudyPlan(timeOne, timeTwo, timeThree, timeFour, timeFive, mondayOne, mondayTwo, mondayThree, mondayFour, mondayFive, tuesdayOne, tuesdayTwo, tuesdayThree, tuesdayFour, tuesdayFive, wednesdayOne, wednesdayTwo, wednesdayThree, wednesdayFour, wednesdayFive, thursdayOne, thursdayTwo, thursdayThree, thursdayFour, thursdayFive, fridayOne, fridayTwo, fridayThree, fridayFour, fridayFive, saturdayOne, saturdayTwo, saturdayThree, saturdayFour, saturdayFive, sundayOne, sundayTwo, sundayThree, sundayFour, sundayFive);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.CREATE_STUDY_PLAN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(CreateStudyPlanActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CreateStudyPlanActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_TIME_ONE, studyPlan.getTimeOne());
                params.put(KEY_TIME_TWO, studyPlan.getTimeTwo());
                params.put(KEY_TIME_THREE, studyPlan.getTimeThree());
                params.put(KEY_TIME_FOUR, studyPlan.getTimeFour());
                params.put(KEY_TIME_FIVE, studyPlan.getTimeFive());
                params.put(KEY_MONDAY_ONE, studyPlan.getMondayOne());
                params.put(KEY_MONDAY_TWO, studyPlan.getMondayTwo());
                params.put(KEY_MONDAY_THREE, studyPlan.getMondayThree());
                params.put(KEY_MONDAY_FOUR, studyPlan.getMondayFour());
                params.put(KEY_MONDAY_FIVE, studyPlan.getMondayFive());
                params.put(KEY_TUESDAY_ONE, studyPlan.getTuesdayOne());
                params.put(KEY_TUESDAY_TWO, studyPlan.getTuesdayTwo());
                params.put(KEY_TUESDAY_THREE, studyPlan.getTuesdayThree());
                params.put(KEY_TUESDAY_FOUR, studyPlan.getTuesdayFour());
                params.put(KEY_TUESDAY_FIVE, studyPlan.getTuesdayFive());
                params.put(KEY_WEDNESDAY_ONE, studyPlan.getWednesdayOne());
                params.put(KEY_WEDNESDAY_TWO, studyPlan.getWednesdayTwo());
                params.put(KEY_WEDNESDAY_THREE, studyPlan.getWednesdayThree());
                params.put(KEY_WEDNESDAY_FOUR, studyPlan.getWednesdayFour());
                params.put(KEY_WEDNESDAY_FIVE, studyPlan.getWednesdayFive());
                params.put(KEY_THURSDAY_ONE, studyPlan.getThursdayOne());
                params.put(KEY_THURSDAY_TWO, studyPlan.getThursdayTwo());
                params.put(KEY_THURSDAY_THREE, studyPlan.getThursdayThree());
                params.put(KEY_THURSDAY_FOUR, studyPlan.getThursdayFour());
                params.put(KEY_THURSDAY_FIVE, studyPlan.getThursdayFive());
                params.put(KEY_FRIDAY_ONE, studyPlan.getFridayOne());
                params.put(KEY_FRIDAY_TWO, studyPlan.getFridayTwo());
                params.put(KEY_FRIDAY_THREE, studyPlan.getFridayThree());
                params.put(KEY_FRIDAY_FOUR, studyPlan.getFridayFour());
                params.put(KEY_FRIDAY_FIVE, studyPlan.getFridayFive());
                params.put(KEY_SATURDAY_ONE, studyPlan.getSaturdayOne());
                params.put(KEY_SATURDAY_TWO, studyPlan.getSaturdayTwo());
                params.put(KEY_SATURDAY_THREE, studyPlan.getSaturdayThree());
                params.put(KEY_SATURDAY_FOUR, studyPlan.getSaturdayFour());
                params.put(KEY_SATURDAY_FIVE, studyPlan.getSaturdayFive());
                params.put(KEY_SUNDAY_ONE, studyPlan.getSundayOne());
                params.put(KEY_SUNDAY_TWO, studyPlan.getSundayTwo());
                params.put(KEY_SUNDAY_THREE, studyPlan.getSundayThree());
                params.put(KEY_SUNDAY_FOUR, studyPlan.getSundayFour());
                params.put(KEY_SUNDAY_FIVE, studyPlan.getSundayFive());
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
