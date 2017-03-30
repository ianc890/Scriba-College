package com.example.scriba.scribacollege;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.scriba.scribacollege.activity.WebViewActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ian C on 24/09/2016.
 */

public class Parser extends AsyncTask<Void, Integer, Integer> {

    Context context;
    ListView lv;
    String data;

    String name;
    String filename;
    private String SERVER_URL = "http://ianc.x10host.com/ScribaCollege/uploads/";

    ArrayList<String> files  = new ArrayList<>();
    ProgressDialog pd;

    public Parser(Context context, ListView lv, String data) {
        this.context = context;
        this.lv = lv;
        this.data = data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(context);
        pd.setTitle("Parser");
        pd.setMessage("Parsing....Please Wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if(integer == 1){
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, files);
            lv.setAdapter(adapter);


            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    //Snackbar.make(view, files.get(position), Snackbar.LENGTH_SHORT).show();

                   /* Intent intent = new Intent(Intent.ACTION_VIEW);

                    intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + SERVER_URL + filename), "text/html");

                    context.startActivity(intent);*/

                    String doc=""+SERVER_URL + files.get(position);

                    //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(doc));
                    //context.startActivity(browserIntent);

                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("file", doc);
                    context.startActivity(intent);

                }
            });
        }else{
            Toast.makeText(context, "Unable to Parse", Toast.LENGTH_SHORT).show();
        }

        pd.dismiss();
    }

    //parse received data
    private int parse()
    {
        try
        {
            //add data to JSON array
            JSONArray ja = new JSONArray(data);

            //create JSON Object to hold single item
            JSONObject jo = null;

            files.clear();

            //loop through array
            for(int i = 0; i < ja.length(); i++){
                jo = ja.getJSONObject(i);

                //retrieve name
                name = jo.getString("path");

                //String path=":/storage/sdcard0/DCIM/Camera/1414240995236.jpg";//it contain your path of image..im using a temp string..
                filename=name.substring(name.lastIndexOf("/")+1);

                ////add to arraylist
                files.add(filename);
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;

    }

}
