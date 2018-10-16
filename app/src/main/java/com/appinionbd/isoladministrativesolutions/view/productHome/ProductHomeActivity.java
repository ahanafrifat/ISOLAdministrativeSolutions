package com.appinionbd.isoladministrativesolutions.view.productHome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appinionbd.isoladministrativesolutions.R;

public class ProductHomeActivity extends AppCompatActivity {

    private String itemId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_home);

        Intent intent = getIntent();
        itemId = intent.getStringExtra("itemId");
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
