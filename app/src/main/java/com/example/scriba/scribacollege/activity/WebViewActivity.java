package com.example.scriba.scribacollege.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scriba.scribacollege.R;
import com.example.scriba.scribacollege.config.Config;
import com.example.scriba.scribacollege.model.File;

import java.util.HashMap;
import java.util.Map;

import no.nordicsemi.android.scriba.hrs.HRSActivity;

public class WebViewActivity extends AppCompatActivity {

    WebView web;
    String myFileUrl;
    private ActionMode mActionMode = null;

    public static final String KEY_CONTENT = "content";
    public static final String KEY_FILE_ID= "fileId";

    public String content;
    private File file;

    public final static String SER_KEY = "com.easyinfogeek.objectPass.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*Intent intent = getIntent();
        myFileUrl = intent.getStringExtra("file");
        Log.i("HERE", myFileUrl);*/

        file = (File)getIntent().getSerializableExtra(MyFilesActivity.SER_KEY);
        myFileUrl = ""+Config.SERVER_URL+file.getFilename();

        String url = "http://docs.google.com/gview?embedded=true&url="+myFileUrl;
        String googleDocsUrl = "http://docs.google.com/viewer?url="+myFileUrl;
        String fileViewer = "https://view.officeapps.live.com/op/view.aspx?src="+myFileUrl;
        //String document = "<iframe src='"+url+"' width='100%' height='100%' style='border: none;'></iframe>";
        web = (WebView) findViewById(R.id.webView1);
        //web.setWebChromeClient(new MyWebChromeClient());
        web.setWebViewClient(new AppWebViewClients());
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().getJavaScriptCanOpenWindowsAutomatically();
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setBuiltInZoomControls(true);
        //web.getSettings().setAllowFileAccess(true);

        web.getSettings().setUseWideViewPort(true);
        web.getSettings().setDisplayZoomControls(false);

        String fileExtension = myFileUrl.substring(myFileUrl.lastIndexOf(".") + 1);
        String fileAsHtml = "https://api.cloudconvert.com/convert?apikey=5vSL13LMgZHuzUFX8t4pSlKdOkz2AKKYA6dIWFQkAkkv5YhbnHTPzVSMOV2dDMw5qz2JAyInzl1uR1-l8QTYxA&inputformat="+fileExtension+"&outputformat=html&input=download&file="+myFileUrl+"&wait=true&download=inline";

        web.loadUrl(fileAsHtml);
       // web.loadData(fileViewer, "text/html", "UTF-8");

        //web.setSelected(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.evaluateJavascript("(document.getElementById(\"page-container\").onclick = function() {\n" +
                        "\t\t// Get Selection\n" +
                        "    sel = window.getSelection();\n" +
                        "    if (sel.rangeCount && sel.getRangeAt) {\n" +
                        "        range = sel.getRangeAt(0);\n" +
                        "    }\n" +
                        "    // Set design mode to on\n" +
                        "    document.designMode = \"on\";\n" +
                        "    if (range) {\n" +
                        "        sel.removeAllRanges();\n" +
                        "        sel.addRange(range);\n" +
                        "    }\n" +
                        "    // Colorize text\n" +
                        "    document.execCommand(\"BackColor\", false, \"red\");\n" +
                        "    // Set design mode to off\n" +
                        "    document.designMode = \"off\";\n" +
                        "    \n" +
                        "    return window.getSelection().toString();\n" +
                        "    })()" ,new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String selectedText) {
                        Log.d("LogName", selectedText); //s is NOT empty!
                        content = selectedText;

                        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
                    }}
                );
                addNote(content);
            }
        });

        displayScribaDialog();
    }

    // displays dialog to connect scriba stylus
    private void displayScribaDialog() {
        Intent intent = new Intent(WebViewActivity.this, HRSActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    // sends the serialized file object to the WebView Activty
    public void serializedFile(){
        Intent mIntent = new Intent(this, NotesActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(SER_KEY, file);
        mIntent.putExtras(mBundle);

        startActivity(mIntent);
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {
        if (mActionMode == null) {
            mActionMode = mode;

            Menu menu = mode.getMenu();
          // MenuItem item1 = menu.findItem(android.R.id.selectAll);
           // item1.setVisible(false);

            menu.clear();
            mode.getMenuInflater().inflate(R.menu.actionmode, menu);

            //menu.add(0, R.id.action_share, 1, "Clear Format");

        }

        super.onActionModeStarted(mode);
    }

    public void onContextualMenuItemClicked(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                shareContent(content);
                break;
            default:
                break;
        }

        // This will likely always be true, but check it anyway, just in case
        if (mActionMode != null) {
            mActionMode.finish();
        }
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        mActionMode = null;
        super.onActionModeFinished(mode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.bluetooth:
                bluetooth(item);
                return true;
            case R.id.notes:
                viewNotes(item);
                return true;
            case R.id.createQuestion:
                createQuestion(item);
                return true;
            case R.id.quiz:
                takeQuiz(item);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void takeQuiz(MenuItem item) {
        Intent intent= new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    public void createQuestion(MenuItem item) {
        Intent intent= new Intent(this, QuizQuestionsActivity.class);
        startActivity(intent);
    }

    public void bluetooth(MenuItem item) {
        Intent intent= new Intent(this, HRSActivity.class);
        startActivity(intent);
    }

    public void viewNotes(MenuItem item) {
        serializedFile();
    }

    private void shareContent(String content) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, content);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.share_with)));
    }

    public class AppWebViewClients extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
        }
    }

    private void addNote(final String noteContent){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.INSERT_NOTE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(WebViewActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(WebViewActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_CONTENT, noteContent);
                params.put(KEY_FILE_ID, String.valueOf(file.getId()));
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
