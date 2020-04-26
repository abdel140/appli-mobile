package com.example.appliandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class ProductActivity extends AppCompatActivity {

    public static void display(Activity activity, Product product){
        Intent intent = new Intent(activity, ProductActivity.class);
        intent.putExtra("product", (Serializable) product);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);
        ImageView product_image = findViewById(R.id.product_image_details);
        TextView product_description = findViewById(R.id.product_description);
        Product product = (Product) getIntent().getExtras().get("product");
        product_description.setText(product.getDescription());
        Picasso.get().load(product.getPicture_url()).into(product_image);
    }
}
