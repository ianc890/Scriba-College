package com.example.scriba.scribacollege.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.scriba.scribacollege.R;
import com.example.scriba.scribacollege.model.File;

import java.util.List;

/**
 * @author Ian Cunningham
 */

public class CustomAdapter extends ArrayAdapter<File> implements ListAdapter {

    List<File> listItems;
    Context context;

    public CustomAdapter(Context context, int resID, List<File> array){
        super(context, resID, array);

        listItems = array;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        View view = convertView;
        TextView nameTv;
        ImageView imageView;

        if(view != null){
            nameTv = (TextView) view.findViewById(R.id.firstLine);
            imageView = (ImageView) view.findViewById(R.id.icon);
        }else {
            LayoutInflater lf = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = lf.inflate(R.layout.files_listview_item, null);
            nameTv = (TextView) view.findViewById(R.id.firstLine);
            imageView = (ImageView) view.findViewById(R.id.icon);
        }

        String fileExtension = listItems.get(position).getFilename().substring(listItems.get(position).getFilename().lastIndexOf(".") + 1);

        if(fileExtension.equals("pdf")) {
            imageView.setImageResource(R.mipmap.ic_pdf);
        } else if(fileExtension.equals("docx") || fileExtension.equals("doc")) {
            imageView.setImageResource(R.mipmap.ic_docx);
        } else if(fileExtension.equals("xlsx") || fileExtension.equals("xlsm") || fileExtension.equals("xltx") || fileExtension.equals("xltm")) {
            imageView.setImageResource(R.mipmap.ic_excel);
        } else if(fileExtension.equals("pptx") || fileExtension.equals("pptm") || fileExtension.equals("ppt")) {
            imageView.setImageResource(R.mipmap.ic_pptx);
        }

        nameTv.setText(listItems.get(position).getFilename());

       /* if (imageURL.isEmpty()) {
            imageURL = null;
        }

        // loading Image from URL using Picasso
        Picasso.with(getContext())
                .load(imageURL)
                .placeholder(R.mipmap.ic_launcher_prem)
                .error(R.mipmap.ic_launcher_prem)
                .into(imageView);*/

        return view;
    }

    @Override
    public int getCount(){
        return listItems.size();
    }
}
