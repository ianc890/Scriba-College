package com.example.scriba.scribacollege.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scriba.scribacollege.R;
import com.example.scriba.scribacollege.config.Config;
import com.example.scriba.scribacollege.model.MindMap;

import java.util.HashMap;
import java.util.Map;

public class MindMapActivity extends AppCompatActivity {

    public static final String KEY_ROOT = "root";
    public static final String KEY_LEAF_ONE= "leaf_one";
    public static final String KEY_LEAF_TWO= "leaf_two";
    public static final String KEY_LEAF_THREE= "leaf_three";
    public static final String KEY_LEAF_FOUR= "leaf_four";
    public static final String KEY_LEAF_FIVE= "leaf_five";
    public static final String KEY_LEAF_SIX= "leaf_six";
    public static final String KEY_LEAF_SEVEN= "leaf_seven";
    public static final String KEY_LEAF_EIGHT= "leaf_eight";


    private EditText rootET;
    private EditText leafOneET;
    private EditText leafTwoET;
    private EditText leafThreeET;
    private EditText leafFourET;
    private EditText leafFiveET;
    private EditText leafSixET;
    private EditText leafSevenET;
    private EditText leafEightET;

    private Button buttonMindMap;

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

        buttonMindMap = (Button) findViewById(R.id.button_save);

        buttonMindMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == buttonMindMap){
                    createMindMap();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mind_map_menu, menu);
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

    private void createMindMap(){

        final String root = rootET.getText().toString();
        final String leafOne = leafOneET.getText().toString();
        final String leafTwo = leafTwoET.getText().toString();
        final String leafThree = leafThreeET.getText().toString();
        final String leafFour = leafFourET.getText().toString();
        final String leafFive = leafFiveET.getText().toString();
        final String leafSix = leafSixET.getText().toString();
        final String leafSeven = leafSevenET.getText().toString();
        final String leafEight = leafEightET.getText().toString();

        final MindMap mindMap = new MindMap(root, leafOne, leafTwo, leafThree, leafFour, leafFive, leafSix, leafSeven, leafEight);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.CREATE_MIND_MAP_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MindMapActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MindMapActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_ROOT, mindMap.getRoot());
                params.put(KEY_LEAF_ONE, mindMap.getLeafOne());
                params.put(KEY_LEAF_TWO, mindMap.getLeafTwo());
                params.put(KEY_LEAF_THREE, mindMap.getLeafThree());
                params.put(KEY_LEAF_FOUR, mindMap.getLeafFour());
                params.put(KEY_LEAF_FIVE, mindMap.getLeafFive());
                params.put(KEY_LEAF_SIX, mindMap.getLeafSix());
                params.put(KEY_LEAF_SEVEN, mindMap.getLeafSeven());
                params.put(KEY_LEAF_EIGHT, mindMap.getLeafEight());
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void viewAll(MenuItem item) {
        Intent intent = new Intent(MindMapActivity.this, MyMindMapsActivity.class);
        startActivity(intent);
    }
}
