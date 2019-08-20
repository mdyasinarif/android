package com.yasin.sqlfinalcurd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
TextView idc,name,quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        idc = findViewById(R.id.viewPid);
        name = findViewById(R.id.viewName);
        quantity = findViewById(R.id.viewQty);

        Intent intent = getIntent();

        MyDbAdapter dbAdapter = new MyDbAdapter(this);

        ProductAdapter adapter = new ProductAdapter(this,dbAdapter.getList());
        int position = intent.getIntExtra("pos",1);

        long pid = adapter.getItemId(position);

        Product product = dbAdapter.findProductById((int)pid);

        idc.setText(String.valueOf(product.getId()));
        name.setText(String.valueOf(product.getProductname()));
        quantity.setText(String.valueOf(product.getQuantity()));

    }
}
