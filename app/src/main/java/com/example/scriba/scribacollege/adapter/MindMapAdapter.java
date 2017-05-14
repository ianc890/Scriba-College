package com.example.scriba.scribacollege.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.scriba.scribacollege.R;
import com.example.scriba.scribacollege.model.MindMap;

import java.util.List;

/**
 * @author Ian Cunningham
 */

public class MindMapAdapter extends ArrayAdapter<MindMap> implements ListAdapter {

    List<MindMap> listItems;
    Context context;

    public MindMapAdapter(Context context, int resID, List<MindMap> array){
        super(context, resID, array);

        listItems = array;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        View view = convertView;
        TextView nameTv;

        if(view != null){
            nameTv = (TextView) view.findViewById(R.id.name);
        }else {
            LayoutInflater lf = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = lf.inflate(R.layout.list_view_string, null);
            nameTv = (TextView) view.findViewById(R.id.name);
        }

        nameTv.setText(listItems.get(position).getRoot());

        return view;
    }

    @Override
    public int getCount(){
        return listItems.size();
    }
}
