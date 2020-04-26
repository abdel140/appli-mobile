package com.example.appliandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> productesList;
    ProductAdapter(Context context, ArrayList<Product> productesList){
        this.context = context;
        this.productesList = productesList;
    }


    @Override
    public int getCount() {
        return productesList.size();
    }

    @Override
    public Object getItem(int position) {
        return productesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_products_details,parent,false);
        }

        TextView title; TextView desciption;
        ImageView img;

        title = convertView.findViewById(R.id.product_name);
        desciption = convertView.findViewById(R.id.product_description);
        img = convertView.findViewById(R.id.product_image);

        title.setText(productesList.get(position).getName());
        desciption.setText(productesList.get(position).getDescription());
        Picasso.get().load(productesList.get(position).getPicture_url()).into(img);

        return convertView;
    }
}