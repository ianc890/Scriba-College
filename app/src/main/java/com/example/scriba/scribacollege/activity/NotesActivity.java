package com.example.scriba.scribacollege.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.scriba.scribacollege.R;
import com.example.scriba.scribacollege.adapter.NoteAdapter;
import com.example.scriba.scribacollege.config.Config;
import com.example.scriba.scribacollege.model.File;
import com.example.scriba.scribacollege.model.Note;

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

public class NotesActivity extends AppCompatActivity {

    private String myJSON;

    private static final String TAG_FILE_ID = "fileid";
    private static final String TAG_RESULTS="result";
    private static final String TAG_CONTENT ="content";
    private static final String TAG_CREATED_AT ="date";

    JSONArray jsonFiles = null;

    List<Note> notesList;
    Map<String,String> notesMap;
    ListView list;

    private String fileMsg;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        file = (File)getIntent().getSerializableExtra(WebViewActivity.SER_KEY);
        this.setTitle("Notes ("+file.getFilename()+")");

        list = (ListView) findViewById(R.id.notes_listview);
        notesList = new ArrayList<>();

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

    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            jsonFiles = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i = 0; i< jsonFiles.length(); i++){

                String content = null;
                String createdAt = null;
                int fileId = 0;

                try {
                    JSONObject c = jsonFiles.getJSONObject(i);
                    {

                    }
                    content = c.getString(TAG_CONTENT);
                    createdAt = c.getString(TAG_CREATED_AT);
                    fileId = c.getInt(TAG_FILE_ID);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                notesMap = new HashMap<String,String>();

                notesMap.put(TAG_CONTENT, content);
                notesMap.put(TAG_CREATED_AT, createdAt);
                notesMap.put(TAG_FILE_ID, String.valueOf(fileId));


                    Note note = new Note(content, createdAt, fileId);
                Log.e("HERE", file.getId()+"---------------"+fileId);
                if(file.getId() == fileId) {
                    notesList.add(note);
                }
            }

            NoteAdapter adapter = new NoteAdapter(NotesActivity.this, R.layout.notes_bubble, notesList);

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
                URL url = new URL(Config.RETRIEVE_NOTES_URL);
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
