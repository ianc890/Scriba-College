package com.example.scriba.scribacollege.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.scriba.scribacollege.R;
import com.example.scriba.scribacollege.adapter.CustomAdapter;
import com.example.scriba.scribacollege.config.Config;
import com.example.scriba.scribacollege.model.File;

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

public class MyFilesActivity extends AppCompatActivity {
    String myJSON;

    private static final String TAG_FILE_ID = "id";
    private static final String TAG_RESULTS="result";
    private static final String TAG_FILENAME ="filename";
    private static final String TAG_FILEPATH ="filepath";

    JSONArray jsonFiles = null;

    List<File> filesList;
    Map<String,String> filesMap;
    ListView list;

    String fileMsg;

    public final static String SER_KEY = "com.easyinfogeek.objectPass.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent2 = getIntent();
        fileMsg = intent2.getStringExtra("FILE_I_NEED");

        list = (ListView) findViewById(R.id.filesListView);
        filesList = new ArrayList<>();

        RetrieveJSONData retrieve = new RetrieveJSONData();
        retrieve.execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*String file = ""+ Config.SERVER_URL+notesList.get(position).getFilename();

                Intent i = new Intent(getApplicationContext(), WebViewActivity.class);
                i.putExtra("file", file);
                startActivity(i);
                Log.i("HERE", String.valueOf(notesList.get(position).getId()));*/

                serializedFile(position);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });
    }

    // refreshes the activity screen
    private void refresh() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    // sends the serialized file object to the WebView Activty
    public void serializedFile(int position){
        File file = filesList.get(position);
        Intent mIntent = new Intent(this, WebViewActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(SER_KEY, file);
        mIntent.putExtras(mBundle);

        startActivity(mIntent);
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

    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            jsonFiles = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i = 0; i< jsonFiles.length(); i++){

                int id = 0;
                String filename = null;
                String filepath = null;

                try {
                    JSONObject c = jsonFiles.getJSONObject(i);
                    {

                    }
                    id = c.getInt("id");
                    filename = c.getString(TAG_FILENAME);
                    filepath = c.getString(TAG_FILEPATH);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                filesMap = new HashMap<String,String>();

                filesMap.put(TAG_FILE_ID, String.valueOf(id));
                filesMap.put(TAG_FILENAME, filename);
                filesMap.put(TAG_FILEPATH, filepath);

                File file = new File(id, filename, filepath);
                filesList.add(file);
            }

            CustomAdapter adapter = new CustomAdapter(MyFilesActivity.this, R.layout.files_listview_item, filesList);

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
                URL url = new URL(Config.RETRIEVE_FILES_URL);
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
            showList();
        }
    }
}

