package com.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomeAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    private String [] cities;

    public CustomeAdapter(Context context, String[] cities) {
        this.context = context;
        this.cities = cities;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.layout_view,null);
        TextView city = view.findViewById(R.id.city);
        city.setText(cities[i]);
        return view;
    }
}
