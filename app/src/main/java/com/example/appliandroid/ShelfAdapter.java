package com.example.appliandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ShelfAdapter extends BaseAdapter {
    Context context;
    ArrayList<Shelf> arrayShelves;

    @Override
    public int getCount() {
        return arrayShelves.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayShelves.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.shelf_name,parent,false);
        }
        TextView shelf_text_name = (TextView) convertView.findViewById(R.id.shelf_text_name);
        shelf_text_name.setText(arrayShelves.get(position).getTitle());
        return convertView;
    }
}
