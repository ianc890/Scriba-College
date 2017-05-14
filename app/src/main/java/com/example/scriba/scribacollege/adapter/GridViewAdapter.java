package com.example.scriba.scribacollege.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.scriba.scribacollege.R;

/**
 * @author Ian Cunningham
 */

public class GridViewAdapter extends BaseAdapter {

    private Context mContext;
    private final String[] gridViewString;
    private final int[] gridViewImageId;

    // constructor
    public GridViewAdapter(Context c, String[] gridViewString, int[] gridViewImageId) {
        mContext = c;
        this.gridViewString = gridViewString;
        this.gridViewImageId = gridViewImageId;
    }

    public int getCount() {
        return gridViewString.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        View gridView;

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            gridView = new View(mContext);
            gridView = inflater.inflate(R.layout.grid_view_layout, null);
            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
            ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
            textView.setText(gridViewString[position]);
            imageView.setImageResource(gridViewImageId[position]);
        } else {
            gridView = convertView;
        }

        return gridView;
    }
}
