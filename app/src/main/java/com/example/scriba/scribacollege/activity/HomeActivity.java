package com.example.scriba.scribacollege.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.scriba.scribacollege.R;
import com.example.scriba.scribacollege.adapter.GridViewAdapter;
import com.example.scriba.scribacollege.config.Config;

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

public class HomeActivity extends AppCompatActivity {

    private static final String TAG_RESULTS = "result";
    private static final String TAG_SUBJECT = "subject";

    GridView gridView;
    private String myJSON;
    private List<String> subjectList = new ArrayList<>();
    Map<String, String> subjectsMap;
    JSONArray jsonFiles = null;
    String email;

    public String[] ACTIVITY_NAMES = {
            "Upload", "My Files",
            "Create Questions", "Quiz",
            "Create Study Plan", "View Study Plan",
            "Create Mind Map", "Personal Assistant"
    };

    public int[] THUMBS_ID = {
            R.mipmap.ic_file_upload_white_24dp, R.mipmap.ic_insert_drive_file_white_24dp,
            R.mipmap.ic_question_answer_white_24dp, R.mipmap.ic_question_answer_white_24dp,
            R.mipmap.ic_assignment_white_24dp, R.mipmap.ic_assignment_white_24dp,
            R.mipmap.ic_map_white_24dp, R.mipmap.ic_person_pin_white_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "Not Available");

        RetrieveJSONData retrieve = new RetrieveJSONData();
        retrieve.execute();

        GridViewAdapter adapter = new GridViewAdapter(HomeActivity.this, ACTIVITY_NAMES, THUMBS_ID);
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                String activityName = ACTIVITY_NAMES[i];

                if (activityName.equals("Upload")) {
                    Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
                    startActivity(intent);
                } else if (activityName.equals("My Files")) {
                    Intent intent = new Intent(getApplicationContext(), MyFilesActivity.class);
                    startActivity(intent);
                } else if (activityName.equals("Notes")) {
                    Intent intent = new Intent(getApplicationContext(), NotesActivity.class);
                    startActivity(intent);
                } else if (activityName.equals("Create Questions")) {
                    Intent intent = new Intent(getApplicationContext(), QuizQuestionsActivity.class);
                    startActivity(intent);
                } else if (activityName.equals("Quiz")) {
                    selectSubjectForQuiz();
                } else if (activityName.equals("Create Study Plan")) {
                    Intent intent = new Intent(getApplicationContext(), CreateStudyPlanActivity.class);
                    startActivity(intent);
                } else if (activityName.equals("View Study Plan")) {
                    Intent intent = new Intent(getApplicationContext(), StudyPlanActivity.class);
                    startActivity(intent);
                } else if (activityName.equals("Create Mind Map")) {
                    Intent intent = new Intent(getApplicationContext(), MindMapActivity.class);
                    startActivity(intent);
                } else if (activityName.equals("View Mind Map")) {
                    Intent intent = new Intent(getApplicationContext(), MyMindMapsActivity.class);
                    startActivity(intent);
                } else if (activityName.equals("Personal Assistant")) {
                    Intent intent = new Intent(getApplicationContext(), ChatbotActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.menuLogout) {
            //calling logout method when the logout button is clicked
            logout();
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        // create an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        // getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        // get the editor
                        SharedPreferences.Editor editor = preferences.edit();

                        // saving the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        // saving blank value to email
                        editor.putString(Config.EMAIL_SHARED_PREF, "");

                        // saving the sharedpreferences
                        editor.commit();

                        // starting login activity
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        // show the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


    private void selectSubjectForQuiz() {
        // create alert dialog to select subject
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Take Quiz");
        alertDialogBuilder.setMessage("Please select a subject!");

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.subject_view, null);

        alertDialogBuilder.setView(view);

        final EditText subjectET = (EditText) view.findViewById(R.id.edit1);

        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        String chosenSubject = String.valueOf(subjectET.getText());
                        Log.e("SUBJECT", chosenSubject);

                        for (int i = 0; i < subjectList.size(); i++) {
                            Log.e("SUBJECTTWO", subjectList.get(i));

                            if (!chosenSubject.equalsIgnoreCase(subjectList.get(i))) {
                                Toast.makeText(HomeActivity.this, "Subject does not exist!", Toast.LENGTH_SHORT).show();
                            } else {
                                // start the quiz activity
                                Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
                                intent.putExtra("subject_chosen", chosenSubject);
                                startActivity(intent);
                            }
                        }

                    }
                });

        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // return to upload activity
                        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                });

        // show the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    protected void getSubjectList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            jsonFiles = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < jsonFiles.length(); i++) {

                String subject = null;

                try {
                    JSONObject c = jsonFiles.getJSONObject(i);
                    {

                    }
                    subject = c.getString(TAG_SUBJECT);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                subjectsMap = new HashMap<String, String>();

                subjectsMap.put(TAG_SUBJECT, subject);

                subjectList.add(subject);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class RetrieveJSONData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            InputStream inputStream = null;
            String result = null;
            try {
                URL url = new URL(Config.RETRIEVE_SUBJECTS_URL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                inputStream = new BufferedInputStream(con.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                result = sb.toString();
            } catch (Exception e) {

            } finally {
                try {
                    if (inputStream != null) inputStream.close();
                } catch (Exception squish) {
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            myJSON = result;
            getSubjectList();
        }
    }
}
