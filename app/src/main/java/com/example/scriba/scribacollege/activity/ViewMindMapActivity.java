package com.example.scriba.scribacollege.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.scriba.scribacollege.R;
import com.example.scriba.scribacollege.model.MindMap;

public class ViewMindMapActivity extends AppCompatActivity {

    private MindMap mindMap;

    private TextView rootTV;
    private TextView leafOneTV, leafTwoTV, leafThreeTV, leafFourTV, leafFiveTV, leafSixTV, leafSevenTV, leafEightTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mind_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mindMap = (MindMap) getIntent().getSerializableExtra(MyMindMapsActivity.SER_KEY);

        rootTV = (TextView) findViewById(R.id.root);
        leafOneTV = (TextView) findViewById(R.id.leafOne);
        leafTwoTV = (TextView) findViewById(R.id.leafTwo);
        leafThreeTV = (TextView) findViewById(R.id.leafThree);
        leafFourTV = (TextView) findViewById(R.id.leafFour);
        leafFiveTV = (TextView) findViewById(R.id.leafFive);
        leafSixTV = (TextView) findViewById(R.id.leafSix);
        leafSevenTV = (TextView) findViewById(R.id.leafSeven);
        leafEightTV = (TextView) findViewById(R.id.leafEight);

        rootTV.setText(mindMap.getRoot());
        leafOneTV.setText(mindMap.getLeafOne());
        leafTwoTV.setText(mindMap.getLeafTwo());
        leafThreeTV.setText(mindMap.getLeafThree());
        leafFourTV.setText(mindMap.getLeafFour());
        leafFiveTV.setText(mindMap.getLeafFive());
        leafSixTV.setText(mindMap.getLeafSix());
        leafSevenTV.setText(mindMap.getLeafSeven());
        leafEightTV.setText(mindMap.getLeafEight());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed(); // closes the current activity and returns to previous activity in the lifecycle
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
