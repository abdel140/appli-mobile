package com.example.appliandroid;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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

public class ShelfActivity extends BaseAppliActivity {
    public final String CATEGORY_URI = "http://djemam.com/epsi/categories.json";
    public final String ACTIVITY_NAME = "Categories";
    ListView listView;
    ArrayList<Shelf> shelvesList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);
        TextView tv =findViewById(R.id.textNomAppli);
        tv.setText(ACTIVITY_NAME);
        showgoBackButton();
        listView = findViewById(R.id.lv_rayons);
        shelvesList = new ArrayList();
        new fetchData().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductsListActivity.display(ShelfActivity.this, shelvesList.get(position));
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
            shelvesList.clear();
            String result = null;
            try {
                URL url = new URL(CATEGORY_URI);
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
                    String products_url = jsonObject.getString("products_url");
                    String title = jsonObject.getString("title");
                    String shelf_id = jsonObject.getString("category_id");
                    Shelf shelf = new Shelf(shelf_id,title,products_url);
                    shelvesList.add(shelf);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            ShelfAdapter adapter = new ShelfAdapter(ShelfActivity.this,shelvesList);
            listView.setAdapter(adapter);

        }
    }
}


