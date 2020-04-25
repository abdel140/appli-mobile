package com.example.appliandroid;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class ShelfAdapter extends BaseAdapter {
    Context context;
    ArrayList<Shelf> shelvesList;

    public ShelfAdapter(Context context, ArrayList<Shelf> shelvesList){
        this.context = context;
        this.shelvesList = shelvesList;
    }
    
    
    @Override
    public int getCount() {
        return shelvesList.size();
    }

    @Override
    public Object getItem(int position) {
        return shelvesList.get(position);
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
        shelf_text_name.setText(shelvesList.get(position).getTitle());
        return convertView;
    }

}
