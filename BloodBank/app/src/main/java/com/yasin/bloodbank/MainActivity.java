package com.yasin.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void request_blood(View view)
    {
        Intent intent=new Intent(this,RequestActivity.class);
        startActivity(intent);
    }

    public void register_doner(View view)
    {
        Intent intent=new Intent(this,DonateActivity.class);
        startActivity(intent);
    }
}
