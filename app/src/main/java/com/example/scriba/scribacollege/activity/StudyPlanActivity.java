package com.example.scriba.scribacollege.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.scriba.scribacollege.R;
import com.example.scriba.scribacollege.config.Config;
import com.example.scriba.scribacollege.model.StudyPlan;

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

public class StudyPlanActivity extends AppCompatActivity {

    private String myJSON;
    private JSONArray jsonFiles = null;
    private List<StudyPlan> studyPlanList = new ArrayList<>();
    Map<String,String> studyPlanMap;

    public static final String KEY_RESULTS="result";
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

    private TextView textViewTimeOne, textViewTimeTwo, textViewTimeThree, textViewTimeFour, textViewTimeFive;
    private TextView textViewMondayOne, textViewMondayTwo, textViewMondayThree, textViewMondayFour, textViewMondayFive;
    private TextView textViewTuesdayOne, textViewTuesdayTwo, textViewTuesdayThree, textViewTuesdayFour, textViewTuesdayFive;
    private TextView textViewWednesdayOne, textViewWednesdayTwo, textViewWednesdayThree, textViewWednesdayFour, textViewWednesdayFive;
    private TextView textViewThursdayOne, textViewThursdayTwo, textViewThursdayThree, textViewThursdayFour, textViewThursdayFive;
    private TextView textViewFridayOne, textViewFridayTwo, textViewFridayThree, textViewFridayFour, textViewFridayFive;
    private TextView textViewSaturdayOne, textViewSaturdayTwo, textViewSaturdayThree, textViewSaturdayFour, textViewSaturdayFive;
    private TextView textViewSundayOne, textViewSundayTwo, textViewSundayThree, textViewSundayFour, textViewSundayFive;

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewTimeOne = (TextView) findViewById(R.id.textViewTimeOne);
        textViewTimeTwo = (TextView) findViewById(R.id.textViewTimeTwo);
        textViewTimeThree = (TextView) findViewById(R.id.textViewTimeThree);
        textViewTimeFour = (TextView) findViewById(R.id.textViewTimeFour);
        textViewTimeFive = (TextView) findViewById(R.id.textViewTimeFive);

        textViewMondayOne = (TextView) findViewById(R.id.textViewMondayOne);
        textViewMondayTwo = (TextView) findViewById(R.id.textViewMondayTwo);
        textViewMondayThree = (TextView) findViewById(R.id.textViewMondayThree);
        textViewMondayFour = (TextView) findViewById(R.id.textViewMondayFour);
        textViewMondayFive = (TextView) findViewById(R.id.textViewMondayFive);

        textViewTuesdayOne = (TextView) findViewById(R.id.textViewTuesdayOne);
        textViewTuesdayTwo = (TextView) findViewById(R.id.textViewTuesdayTwo);
        textViewTuesdayThree = (TextView) findViewById(R.id.textViewTuesdayThree);
        textViewTuesdayFour = (TextView) findViewById(R.id.textViewTuesdayFour);
        textViewTuesdayFive = (TextView) findViewById(R.id.textViewTuesdayFive);

        textViewWednesdayOne = (TextView) findViewById(R.id.textViewWednesdayOne);
        textViewWednesdayTwo = (TextView) findViewById(R.id.textViewWednesdayTwo);
        textViewWednesdayThree = (TextView) findViewById(R.id.textViewWednesdayThree);
        textViewWednesdayFour = (TextView) findViewById(R.id.textViewWednesdayFour);
        textViewWednesdayFive = (TextView) findViewById(R.id.textViewWednesdayFive);

        textViewThursdayOne = (TextView) findViewById(R.id.textViewThursdayOne);
        textViewThursdayTwo = (TextView) findViewById(R.id.textViewThursdayTwo);
        textViewThursdayThree = (TextView) findViewById(R.id.textViewThursdayThree);
        textViewThursdayFour = (TextView) findViewById(R.id.textViewThursdayFour);
        textViewThursdayFive = (TextView) findViewById(R.id.textViewThursdayFive);

        textViewFridayOne = (TextView) findViewById(R.id.textViewFridayOne);
        textViewFridayTwo = (TextView) findViewById(R.id.textViewFridayTwo);
        textViewFridayThree = (TextView) findViewById(R.id.textViewFridayThree);
        textViewFridayFour = (TextView) findViewById(R.id.textViewFridayFour);
        textViewFridayFive = (TextView) findViewById(R.id.textViewFridayFive);

        textViewSaturdayOne = (TextView) findViewById(R.id.textViewSaturdayOne);
        textViewSaturdayTwo = (TextView) findViewById(R.id.textViewSaturdayTwo);
        textViewSaturdayThree = (TextView) findViewById(R.id.textViewSaturdayThree);
        textViewSaturdayFour = (TextView) findViewById(R.id.textViewSaturdayFour);
        textViewSaturdayFive = (TextView) findViewById(R.id.textViewSaturdayFive);

        textViewSundayOne = (TextView) findViewById(R.id.textViewSundayOne);
        textViewSundayTwo = (TextView) findViewById(R.id.textViewSundayTwo);
        textViewSundayThree = (TextView) findViewById(R.id.textViewSundayThree);
        textViewSundayFour = (TextView) findViewById(R.id.textViewSundayFour);
        textViewSundayFive = (TextView) findViewById(R.id.textViewSundayFive);

        tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        RetrieveJSONData retrieve = new RetrieveJSONData();
        retrieve.execute();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

    protected void showStudyPlan(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            jsonFiles = jsonObj.getJSONArray(KEY_RESULTS);

            for(int i = 0; i< jsonFiles.length(); i++){

                String timeOne = null;
                String timeTwo = null;
                String timeThree = null;
                String timeFour = null;
                String timeFive = null;

                String mondayOne = null;
                String mondayTwo = null;
                String mondayThree = null;
                String mondayFour = null;
                String mondayFive = null;

                String tuesdayOne = null;
                String tuesdayTwo = null;
                String tuesdayThree = null;
                String tuesdayFour = null;
                String tuesdayFive = null;

                String wednesdayOne = null;
                String wednesdayTwo = null;
                String wednesdayThree = null;
                String wednesdayFour = null;
                String wednesdayFive = null;

                String thursdayOne = null;
                String thursdayTwo = null;
                String thursdayThree = null;
                String thursdayFour = null;
                String thursdayFive = null;

                String fridayOne = null;
                String fridayTwo = null;
                String fridayThree = null;
                String fridayFour = null;
                String fridayFive = null;

                String saturdayOne = null;
                String saturdayTwo = null;
                String saturdayThree = null;
                String saturdayFour = null;
                String saturdayFive = null;

                String sundayOne = null;
                String sundayTwo = null;
                String sundayThree = null;
                String sundayFour = null;
                String sundayFive = null;

                try {
                    JSONObject c = jsonFiles.getJSONObject(i);
                    {

                    }
                    timeOne = c.getString(KEY_TIME_ONE);
                    timeTwo = c.getString(KEY_TIME_TWO);
                    timeThree = c.getString(KEY_TIME_THREE);
                    timeFour = c.getString(KEY_TIME_FOUR);
                    timeFive = c.getString(KEY_TIME_FIVE);

                    mondayOne = c.getString(KEY_MONDAY_ONE);
                    mondayTwo = c.getString(KEY_MONDAY_TWO);
                    mondayThree = c.getString(KEY_MONDAY_THREE);
                    mondayFour = c.getString(KEY_MONDAY_FOUR);
                    mondayFive = c.getString(KEY_MONDAY_FIVE);

                    tuesdayOne = c.getString(KEY_TUESDAY_ONE);
                    tuesdayTwo = c.getString(KEY_TUESDAY_TWO);
                    tuesdayThree = c.getString(KEY_TUESDAY_THREE);
                    tuesdayFour = c.getString(KEY_TUESDAY_FOUR);
                    tuesdayFive = c.getString(KEY_TUESDAY_FIVE);

                    wednesdayOne = c.getString(KEY_WEDNESDAY_ONE);
                    wednesdayTwo = c.getString(KEY_WEDNESDAY_TWO);
                    wednesdayThree = c.getString(KEY_WEDNESDAY_THREE);
                    wednesdayFour = c.getString(KEY_WEDNESDAY_FOUR);
                    wednesdayFive = c.getString(KEY_WEDNESDAY_FIVE);

                    thursdayOne = c.getString(KEY_THURSDAY_ONE);
                    thursdayTwo = c.getString(KEY_THURSDAY_TWO);
                    thursdayThree = c.getString(KEY_THURSDAY_THREE);
                    thursdayFour = c.getString(KEY_THURSDAY_FOUR);
                    thursdayFive = c.getString(KEY_THURSDAY_FIVE);

                    fridayOne = c.getString(KEY_FRIDAY_ONE);
                    fridayTwo = c.getString(KEY_FRIDAY_TWO);
                    fridayThree = c.getString(KEY_FRIDAY_THREE);
                    fridayFour = c.getString(KEY_FRIDAY_FOUR);
                    fridayFive = c.getString(KEY_FRIDAY_FIVE);

                    saturdayOne = c.getString(KEY_SATURDAY_ONE);
                    saturdayTwo = c.getString(KEY_SATURDAY_TWO);
                    saturdayThree = c.getString(KEY_SATURDAY_THREE);
                    saturdayFour = c.getString(KEY_SATURDAY_FOUR);
                    saturdayFive = c.getString(KEY_SATURDAY_FIVE);

                    sundayOne = c.getString(KEY_SUNDAY_ONE);
                    sundayTwo = c.getString(KEY_SUNDAY_TWO);
                    sundayThree = c.getString(KEY_SUNDAY_THREE);
                    sundayFour = c.getString(KEY_SUNDAY_FOUR);
                    sundayFive = c.getString(KEY_SUNDAY_FIVE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                studyPlanMap = new HashMap<String,String>();

                studyPlanMap.put(KEY_TIME_ONE, timeOne);
                studyPlanMap.put(KEY_TIME_TWO, timeTwo);
                studyPlanMap.put(KEY_TIME_THREE, timeThree);
                studyPlanMap.put(KEY_TIME_FOUR, timeFour);
                studyPlanMap.put(KEY_TIME_FIVE, timeFive);
                studyPlanMap.put(KEY_MONDAY_ONE, mondayOne);
                studyPlanMap.put(KEY_MONDAY_TWO, mondayTwo);
                studyPlanMap.put(KEY_MONDAY_THREE, mondayThree);
                studyPlanMap.put(KEY_MONDAY_FOUR, mondayFour);
                studyPlanMap.put(KEY_MONDAY_FIVE, mondayFive);
                studyPlanMap.put(KEY_TUESDAY_ONE, tuesdayOne);
                studyPlanMap.put(KEY_TUESDAY_TWO, tuesdayTwo);
                studyPlanMap.put(KEY_TUESDAY_THREE, tuesdayThree);
                studyPlanMap.put(KEY_TUESDAY_FOUR, tuesdayFour);
                studyPlanMap.put(KEY_TUESDAY_FIVE, tuesdayFive);
                studyPlanMap.put(KEY_WEDNESDAY_ONE, wednesdayOne);
                studyPlanMap.put(KEY_WEDNESDAY_TWO, wednesdayTwo);
                studyPlanMap.put(KEY_WEDNESDAY_THREE, wednesdayThree);
                studyPlanMap.put(KEY_WEDNESDAY_FOUR, wednesdayFour);
                studyPlanMap.put(KEY_WEDNESDAY_FIVE, wednesdayFive);
                studyPlanMap.put(KEY_THURSDAY_ONE, thursdayOne);
                studyPlanMap.put(KEY_THURSDAY_TWO, thursdayTwo);
                studyPlanMap.put(KEY_THURSDAY_THREE, thursdayThree);
                studyPlanMap.put(KEY_THURSDAY_FOUR, thursdayFour);
                studyPlanMap.put(KEY_THURSDAY_FIVE, thursdayFive);
                studyPlanMap.put(KEY_FRIDAY_ONE, fridayOne);
                studyPlanMap.put(KEY_FRIDAY_TWO, fridayTwo);
                studyPlanMap.put(KEY_FRIDAY_THREE, fridayThree);
                studyPlanMap.put(KEY_FRIDAY_FOUR, fridayFour);
                studyPlanMap.put(KEY_FRIDAY_FIVE, fridayFive);

                studyPlanMap.put(KEY_SATURDAY_ONE, saturdayOne);
                studyPlanMap.put(KEY_SATURDAY_TWO, saturdayTwo);
                studyPlanMap.put(KEY_SATURDAY_THREE, saturdayThree);
                studyPlanMap.put(KEY_SATURDAY_FOUR, saturdayFour);
                studyPlanMap.put(KEY_SATURDAY_FIVE, saturdayFive);

                studyPlanMap.put(KEY_SUNDAY_ONE, sundayOne);
                studyPlanMap.put(KEY_SUNDAY_TWO, sundayTwo);
                studyPlanMap.put(KEY_SUNDAY_THREE, sundayThree);
                studyPlanMap.put(KEY_SUNDAY_FOUR, sundayFour);
                studyPlanMap.put(KEY_SUNDAY_FIVE, sundayFive);

                StudyPlan studyPlanDetails = new StudyPlan(timeOne, timeTwo, timeThree, timeFour, timeFive, mondayOne, mondayTwo, mondayThree, mondayFour, mondayFive, tuesdayOne, tuesdayTwo, tuesdayThree, tuesdayFour, tuesdayFive, wednesdayOne, wednesdayTwo, wednesdayThree, wednesdayFour, wednesdayFive, thursdayOne, thursdayTwo, thursdayThree, thursdayFour, thursdayFive, fridayOne, fridayTwo, fridayThree, fridayFour, fridayFive, saturdayOne, saturdayTwo, saturdayThree, saturdayFour, saturdayFive, sundayOne, sundayTwo, sundayThree, sundayFour, sundayFive);
                studyPlanList.add(studyPlanDetails);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        textViewTimeOne.setText(studyPlanList.get(0).getTimeOne());
        textViewTimeTwo.setText(studyPlanList.get(0).getTimeTwo());
        textViewTimeThree.setText(studyPlanList.get(0).getTimeThree());
        textViewTimeFour.setText(studyPlanList.get(0).getTimeFour());
        textViewTimeFive.setText(studyPlanList.get(0).getTimeFive());

        textViewMondayOne.setText(studyPlanList.get(0).getMondayOne());
        textViewMondayTwo.setText(studyPlanList.get(0).getMondayTwo());
        textViewMondayThree.setText(studyPlanList.get(0).getMondayThree());
        textViewMondayFour.setText(studyPlanList.get(0).getMondayFour());
        textViewMondayFive.setText(studyPlanList.get(0).getMondayFive());

        textViewTuesdayOne.setText(studyPlanList.get(0).getTuesdayOne());
        textViewTuesdayTwo.setText(studyPlanList.get(0).getTuesdayTwo());
        textViewTuesdayThree.setText(studyPlanList.get(0).getTuesdayThree());
        textViewTuesdayFour.setText(studyPlanList.get(0).getTuesdayFour());
        textViewTuesdayFive.setText(studyPlanList.get(0).getTuesdayFive());

        textViewWednesdayOne.setText(studyPlanList.get(0).getWednesdayOne());
        textViewWednesdayTwo.setText(studyPlanList.get(0).getWednesdayTwo());
        textViewWednesdayThree.setText(studyPlanList.get(0).getWednesdayThree());
        textViewWednesdayFour.setText(studyPlanList.get(0).getWednesdayFour());
        textViewWednesdayFive.setText(studyPlanList.get(0).getWednesdayFive());

        textViewThursdayOne.setText(studyPlanList.get(0).getThursdayOne());
        textViewThursdayTwo.setText(studyPlanList.get(0).getThursdayTwo());
        textViewThursdayThree.setText(studyPlanList.get(0).getThursdayThree());
        textViewThursdayFour.setText(studyPlanList.get(0).getThursdayFour());
        textViewThursdayFive.setText(studyPlanList.get(0).getThursdayFive());

        textViewFridayOne.setText(studyPlanList.get(0).getFridayOne());
        textViewFridayTwo.setText(studyPlanList.get(0).getFridayTwo());
        textViewFridayThree.setText(studyPlanList.get(0).getFridayThree());
        textViewFridayFour.setText(studyPlanList.get(0).getFridayFour());
        textViewFridayFive.setText(studyPlanList.get(0).getFridayFive());

        textViewSaturdayOne.setText(studyPlanList.get(0).getSaturdayOne());
        textViewSaturdayTwo.setText(studyPlanList.get(0).getSaturdayTwo());
        textViewSaturdayThree.setText(studyPlanList.get(0).getSaturdayThree());
        textViewSaturdayFour.setText(studyPlanList.get(0).getSaturdayFour());
        textViewSaturdayFive.setText(studyPlanList.get(0).getSaturdayFive());

        textViewSundayOne.setText(studyPlanList.get(0).getSundayOne());
        textViewSundayTwo.setText(studyPlanList.get(0).getSundayTwo());
        textViewSundayThree.setText(studyPlanList.get(0).getSundayThree());
        textViewSundayFour.setText(studyPlanList.get(0).getSundayFour());
        textViewSundayFive.setText(studyPlanList.get(0).getSundayFive());

    }

    class RetrieveJSONData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            InputStream inputStream = null;
            String result = null;
            try {
                URL url = new URL(Config.RETRIEVE_STUDY_PLAN_URL);
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
            showStudyPlan();
        }
    }

}
