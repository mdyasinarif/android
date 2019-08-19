package com.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ListView listView;
String []cities = {"Dhaka","Sylete","Khulna"};
List<Student> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add(new Student(100,"Yasin","Dhaka"));
        list.add(new Student(100,"Arif","Sylate"));
        list.add(new Student(100,"Sohan","Chitagong"));
        listView = findViewById(R.id.listview);
//        CustomeAdapter adapter = new CustomeAdapter(getApplicationContext(),cities);
        CustomStudentAdaptor adapter = new CustomStudentAdaptor(getApplicationContext(),list);
        listView.setAdapter(adapter);
    }
}
