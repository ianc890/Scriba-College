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
import com.example.scriba.scribacollege.model.Note;

import java.util.List;

/**
 * Created by Ian C on 05/05/2017.
 */

public class NoteAdapter extends ArrayAdapter<Note> implements ListAdapter {

    List<Note> listItems;
    Context context;

    public NoteAdapter(Context context, int resID, List<Note> array){
        super(context, resID, array);

        listItems = array;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        View view = convertView;
        TextView noteTv;

        if(view != null){
            noteTv = (TextView) view.findViewById(R.id.msgr);
        }else {
            LayoutInflater lf = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = lf.inflate(R.layout.notes_bubble, null);
            noteTv = (TextView) view.findViewById(R.id.msgr);
        }

        noteTv.setText(listItems.get(position).getContent());

        return view;
    }

    @Override
    public int getCount(){
        return listItems.size();
    }
}
