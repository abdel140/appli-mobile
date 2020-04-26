package com.example.appliandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

public class ProductsListActivity extends AppCompatActivity {
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
        productArrayList = new ArrayList<>();
        lv_products = findViewById(R.id.lv_products);
        shelf = (Shelf) getIntent().getExtras().get("shelf");
        new fetchData().execute();
        lv_products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductActivity.display(ProductsListActivity.this,productArrayList.get(position));
            }
        });


    }


    private class fetchData extends AsyncTask<String,String,String> {

        @Override

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            productArrayList.clear();
            String result = null;
            try {
                URL url = new URL(shelf.getProducts_url());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput( true );
                conn.setInstanceFollowRedirects( false );
                conn.setRequestMethod( "GET" );
                conn.setRequestProperty( "Content-Type", "application/json");
                conn.setDoInput(true);
                conn.setRequestProperty( "charset", "utf-8");
                conn.setUseCaches(false);
                if(conn.getResponseCode()== HttpURLConnection.HTTP_OK){
                    InputStreamReader inputStream = new InputStreamReader(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(inputStream);
                    StringBuilder stringBuilder = new StringBuilder();
                    String temp;
                    while((temp = reader.readLine())!=null){
                        stringBuilder.append(temp);
                    }
                    result = stringBuilder.toString();
                }
                else{
                    return "error!";
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject obj = new JSONObject(s);
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
}
