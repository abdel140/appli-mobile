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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductsListActivity.display(ShelfActivity.this, shelvesList.get(position));
            }
        });
        htppAsyncTask.execute();
    }

    public HttpAyncTask htppAsyncTask = new HttpAyncTask(CATEGORY_URI, new HttpAyncTask.HttpAsyncListenner() {
        @Override
        public void webServiceDone(String result) {
            initData(result);
        }

        @Override
        public void webServiceError(Exception e) {
            displayToast(e.getMessage());
        }
    });


    private void initData(String s) {
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


