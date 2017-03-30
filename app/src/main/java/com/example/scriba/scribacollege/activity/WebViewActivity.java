package com.example.scriba.scribacollege.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.scriba.scribacollege.R;

public class WebViewActivity extends Activity {

    WebView web;
    String myFileUrl;
    private ActionMode mActionMode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        myFileUrl = intent.getStringExtra("file");
        Log.i("HERE", myFileUrl);

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

        web.loadUrl(fileViewer);
       // web.loadData(fileViewer, "text/html", "UTF-8");

        //web.setSelected(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.evaluateJavascript("(function(){return window.getSelection().toString()})()" ,new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String s) {
                        Log.d("LogName", s); //s is NOT empty!
                        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                    }}
                );
            }
        });


        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileExtension = myFileUrl.substring(myFileUrl.lastIndexOf(".") + 1);
                String fileAsHtml = "https://api.cloudconvert.com/convert?apikey=5vSL13LMgZHuzUFX8t4pSlKdOkz2AKKYA6dIWFQkAkkv5YhbnHTPzVSMOV2dDMw5qz2JAyInzl1uR1-l8QTYxA&inputformat="+fileExtension+"&outputformat=html&input=download&file="+myFileUrl+"&wait=true&download=inline";
               //web.loadUrl("http://www.tutorialspoint.com/android");
                web.loadUrl(fileAsHtml);
            }
        });

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

    public class MyWebChromeClient extends WebChromeClient {

        public void onSelectionStart(WebView view) {
            // Parent class aborts the selection, which seems like a terrible default.
            //Log.i("DroidGap", "onSelectionStart called");
        }
    }
}
