package com.example.scriba.scribacollege.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.scriba.scribacollege.R;

public class MindMapActivity extends AppCompatActivity {

    private EditText rootET;
    private EditText leafOneET;
    private EditText leafTwoET;
    private EditText leafThreeET;
    private EditText leafFourET;
    private EditText leafFiveET;
    private EditText leafSixET;
    private EditText leafSevenET;
    private EditText leafEightET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mind_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rootET = (EditText) findViewById(R.id.root);
        leafOneET = (EditText) findViewById(R.id.leafOne);
        leafTwoET = (EditText) findViewById(R.id.leafTwo);
        leafThreeET = (EditText) findViewById(R.id.leafThree);
        leafFourET = (EditText) findViewById(R.id.leafFour);
        leafFiveET = (EditText) findViewById(R.id.leafFive);
        leafSixET = (EditText) findViewById(R.id.leafSix);
        leafSevenET = (EditText) findViewById(R.id.leafSeven);
        leafEightET = (EditText) findViewById(R.id.leafEight);

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

}
