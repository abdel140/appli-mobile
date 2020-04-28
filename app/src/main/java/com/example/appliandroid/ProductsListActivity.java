package com.example.appliandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ProductsListActivity extends BaseAppliActivity {
    Shelf shelf;
    ListView lv_products;
    ArrayList<Product> productArrayList;


    public static void display(Activity activity, Shelf shelf){
        Intent intent = new Intent(activity,ProductsListActivity.class);
        intent.putExtra("shelf",shelf);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_list);
        showgoBackButton();
        TextView tv = findViewById(R.id.textNomAppli);
        productArrayList = new ArrayList<>();
        lv_products = findViewById(R.id.lv_products);
        shelf = (Shelf) getIntent().getExtras().get("shelf");
        tv.setText(shelf.getTitle());
        lv_products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductActivity.display(ProductsListActivity.this, productArrayList.get(position));
            }
        });
        new HttpAyncTask(shelf.getProducts_url(), new HttpAyncTask.HttpAsyncListenner() {
            @Override
            public void webServiceDone(String result) {
                initData(result);
            }

            @Override
            public void webServiceError(Exception e) {
                displayToast(e.getMessage());
            }
        }).execute();
    }
    private void initData(String result) {
        try {
            JSONObject obj = new JSONObject(result);
            JSONArray items = obj.getJSONArray("items") ;
            for(int i=0;i<items.length();i++){
                JSONObject jsonObject = items.getJSONObject(i);
                String picture_url = jsonObject.getString("picture_url");
                String name = jsonObject.getString("name");
                String description = jsonObject.getString("description");
                Product product = new Product(name,description,picture_url);
                productArrayList.add(product);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ProductAdapter productAdapter = new ProductAdapter(ProductsListActivity.this,productArrayList);
        lv_products.setAdapter(productAdapter);
    }
}
