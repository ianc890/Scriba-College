package com.example.scriba.scribacollege.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.scriba.scribacollege.R;
import com.example.scriba.scribacollege.adapter.MindMapAdapter;
import com.example.scriba.scribacollege.config.Config;
import com.example.scriba.scribacollege.model.MindMap;

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

public class MyMindMapsActivity extends AppCompatActivity {

    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_ROOT = "root";
    private static final String TAG_LEAF_ONE = "leaf_one";
    private static final String TAG_LEAF_TWO = "leaf_two";
    private static final String TAG_LEAF_THREE = "leaf_three";
    private static final String TAG_LEAF_FOUR = "leaf_four";
    private static final String TAG_LEAF_FIVE = "leaf_five";
    private static final String TAG_LEAF_SIX = "leaf_six";
    private static final String TAG_LEAF_SEVEN = "leaf_seven";
    private static final String TAG_LEAF_EIGHT = "leaf_eight";

    JSONArray jsonFiles = null;

    List<MindMap> mindMapList;
    Map<String, String> mindMapsMap;
    ListView list;

    public final static String SER_KEY = "com.easyinfogeek.objectPass.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_mind_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list = (ListView) findViewById(R.id.mindMapsListView);
        mindMapList = new ArrayList<>();

        RetrieveJSONData retrieve = new RetrieveJSONData();
        retrieve.execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                serializedMindMap(position);
            }
        });
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

    // sends the serialized file object to the WebView Activty
    public void serializedMindMap(int position) {
        MindMap mindMap = mindMapList.get(position);
        Intent mIntent = new Intent(this, ViewMindMapActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(SER_KEY, mindMap);
        mIntent.putExtras(mBundle);

        startActivity(mIntent);
    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            jsonFiles = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < jsonFiles.length(); i++) {

                String root = null;
                String leafOne = null;
                String leafTwo = null;
                String leafThree = null;
                String leafFour = null;
                String leafFive = null;
                String leafSix = null;
                String leafSeven = null;
                String leafEight = null;


                try {
                    JSONObject c = jsonFiles.getJSONObject(i);
                    {

                    }
                    root = c.getString(TAG_ROOT);
                    leafOne = c.getString(TAG_LEAF_ONE);
                    leafTwo = c.getString(TAG_LEAF_TWO);
                    leafThree = c.getString(TAG_LEAF_THREE);
                    leafFour = c.getString(TAG_LEAF_FOUR);
                    leafFive = c.getString(TAG_LEAF_FIVE);
                    leafSix = c.getString(TAG_LEAF_SIX);
                    leafSeven = c.getString(TAG_LEAF_SEVEN);
                    leafEight = c.getString(TAG_LEAF_EIGHT);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mindMapsMap = new HashMap<String, String>();

                mindMapsMap.put(TAG_ROOT, root);
                mindMapsMap.put(TAG_LEAF_ONE, leafOne);
                mindMapsMap.put(TAG_LEAF_TWO, leafTwo);
                mindMapsMap.put(TAG_LEAF_THREE, leafThree);
                mindMapsMap.put(TAG_LEAF_FOUR, leafFour);
                mindMapsMap.put(TAG_LEAF_FIVE, leafFive);
                mindMapsMap.put(TAG_LEAF_SIX, leafSix);
                mindMapsMap.put(TAG_LEAF_SEVEN, leafSeven);
                mindMapsMap.put(TAG_LEAF_EIGHT, leafEight);

                MindMap mindMap = new MindMap(root, leafOne, leafTwo, leafThree, leafFour, leafFive, leafSix, leafSeven, leafEight);
                mindMapList.add(mindMap);
            }

            MindMapAdapter adapter = new MindMapAdapter(MyMindMapsActivity.this, R.layout.list_view_string, mindMapList);

            list.setAdapter(adapter);

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
                URL url = new URL(Config.RETRIEVE_MIND_MAPS_URL);
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
            showList();
        }
    }

}
