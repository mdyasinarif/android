package com.yasin.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListItem extends Fragment {
    DonerData donerData = new DonerData();

    public ListItem() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            donerData.id = getArguments().getInt("Id");
            donerData.full_name = getArguments().getString("Name");
            donerData.city = getArguments().getString("City");
            donerData.area = getArguments().getString("Area");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_item_activity, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView name  = (TextView) view.findViewById(R.id.name);
        TextView location  = (TextView) view.findViewById(R.id.location);

        name.setText(donerData.full_name);
        location.setText(donerData.city+", "+donerData.area);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("info", "onClick: "+donerData.id);
                Intent i = new Intent(getActivity(),DetailActivity.class);
                i.putExtra("Id",donerData.id);
                startActivity(i);
            }
        });
    }
}
