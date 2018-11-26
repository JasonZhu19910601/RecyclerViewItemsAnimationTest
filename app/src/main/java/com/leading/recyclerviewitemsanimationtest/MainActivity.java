package com.leading.recyclerviewitemsanimationtest;

import android.os.Bundle;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(20);
        recyclerView.addItemDecoration(spacesItemDecoration);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CustomRecyclerViewAdapter customRecyclerViewAdapter = new CustomRecyclerViewAdapter(getData(), recyclerView);
        recyclerView.setAdapter(customRecyclerViewAdapter);
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("This is Item " + i);
        }
        return data;
    }
}
