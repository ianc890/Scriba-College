package com.example.scriba.scribacollege.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.scriba.scribacollege.R;
import com.example.scriba.scribacollege.adapter.GridViewAdapter;

public class HomeActivity extends AppCompatActivity {

    GridView gridView;

    public String[] ACTIVITY_NAMES = {
            "Upload", "My Files", "Create Questions", "Study Plan",
            "Mind Map", "Personal Assistant"
    };

    public int[] THUMBS_ID = {
            R.mipmap.ic_file_upload_white_24dp, R.mipmap.ic_insert_drive_file_white_24dp,
            R.mipmap.ic_question_answer_white_24dp, R.mipmap.ic_assignment_white_24dp,
            R.mipmap.ic_map_white_24dp, R.mipmap.ic_person_pin_white_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GridViewAdapter adapter = new GridViewAdapter(HomeActivity.this, ACTIVITY_NAMES, THUMBS_ID);
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

}
