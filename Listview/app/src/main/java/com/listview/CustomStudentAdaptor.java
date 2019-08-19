package com.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomStudentAdaptor extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    private List<Student> students;

    public CustomStudentAdaptor(Context context,  List<Student> students) {
        this.context = context;

        this.students = students;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return students.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.layout_student_view, null);
        TextView id = view.findViewById(R.id.ids);
        id.setText(String.valueOf(students.get(i).getId()));
        TextView name = view.findViewById(R.id.name);
        id.setText(String.valueOf(students.get(i).getName()));
        TextView dis = view.findViewById(R.id.district);
        id.setText(String.valueOf(students.get(i).getDistrict()));
        return view;
    }
}
