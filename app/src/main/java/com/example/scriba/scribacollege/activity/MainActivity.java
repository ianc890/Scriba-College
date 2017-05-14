package com.example.scriba.scribacollege.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.scriba.scribacollege.R;
import com.example.scriba.scribacollege.adapter.GridViewAdapter;

public class MainActivity extends AppCompatActivity {

    GridView gridView;

    public String[] ACTIVITY_NAMES = {
            "Upload", "My Files", "Notes", "Create Questions", "Quiz", "Study Plan",
            "Mind Map", "Personal Assistant"
    };

    public int[] THUMBS_ID = {
            R.mipmap.scriba_launcher, R.mipmap.scriba_launcher
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        GridViewAdapter adapter = new GridViewAdapter(MainActivity.this, ACTIVITY_NAMES, THUMBS_ID);
        gridView=(GridView)findViewById(R.id.gridview);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                String activityName = ACTIVITY_NAMES[i];

                if(activityName.equals("Upload")) {
                    Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
                    startActivity(intent);
                } else if(activityName.equals("My Files")) {
                    Intent intent = new Intent(getApplicationContext(), MyFilesActivity.class);
                    startActivity(intent);
                } else if(activityName.equals("Notes")) {
                    Intent intent = new Intent(getApplicationContext(), NotesActivity.class);
                    startActivity(intent);
                } else if(activityName.equals("Create Questions")) {
                    Intent intent = new Intent(getApplicationContext(), QuizQuestionsActivity.class);
                    startActivity(intent);
                } else if(activityName.equals("Quiz")) {
                    Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                    startActivity(intent);
                } else if(activityName.equals("Study Plan")) {
                    Intent intent = new Intent(getApplicationContext(), StudyPlanActivity.class);
                    startActivity(intent);
                } else if(activityName.equals("Mind Map")) {
                    Intent intent = new Intent(getApplicationContext(), StudyPlanActivity.class);
                    startActivity(intent);
                } else if(activityName.equals("Personal Assistant")) {
                    Intent intent = new Intent(getApplicationContext(), ChatbotActivity.class);
                    startActivity(intent);
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
