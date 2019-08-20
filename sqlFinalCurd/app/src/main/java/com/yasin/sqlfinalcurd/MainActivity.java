package com.yasin.sqlfinalcurd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText idx, name, qty;
    MyDbAdapter helper;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idx = findViewById(R.id.productID);
        name = findViewById(R.id.productNAME);
        qty = findViewById(R.id.productQuantity);
        helper = new MyDbAdapter(this);

        listView = findViewById(R.id.listviews);
        List<Product> list = helper.getList();
        ProductAdapter adapter = new ProductAdapter(this,list);
        listView.setAdapter(adapter);


// product details view intreagration
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("pos",position);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"ID :" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void saveProduct(View view){
        Product product = new Product(name.getText().toString(),Integer.parseInt(qty.getText().toString()));
        long i = helper.insertData(product);
        if (i <0 ){
            Message.mesage(this,"Unscessful");
        }else{
            getProductlist();
            Message.mesage(this, "Successfull");
        }
    }

    public void updateProduct(View view){
        Product product = new Product(Integer.parseInt(idx.getText().toString()),name.getText().toString(),Integer.parseInt(qty.getText().toString()));
        long i = helper.updateData(product);
        if (i <0 ){
            Message.mesage(this,"Unscessful");
        }else{
            getProductlist();
            Message.mesage(this, "Successfull");
        }
    }


    public void getProductlist(){
        listView = findViewById(R.id.listviews);
        List<Product> list = helper.getList();
        ProductAdapter adapter = new ProductAdapter(this,list);
        listView.setAdapter(adapter);
    }

    public void getProductByProductId(View view){
        int pid = Integer.parseInt(idx.getText().toString().trim());
        Product p = helper.findProductById(pid);
        if (p != null){
            name.setText(p.getProductname());
            qty.setText(String.valueOf(p.getQuantity()));
        }else{
            Toast.makeText(this,"No Data Exists", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteProductById(View view){
        int pid = Integer.parseInt(idx.getText().toString().trim());
        helper.deleteProduct(pid);
        getProductlist();
        Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
    }
}
