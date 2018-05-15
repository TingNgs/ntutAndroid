package com.example.hw8.hw8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by NGS on 16/05/2018.
 */

public class DataActivity extends AppCompatActivity {

    ListView lsvDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        lsvDataList = (ListView) findViewById(R.id.lsv_DataList);

        Intent intent = getIntent();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adapter.addAll(intent.getStringArrayListExtra("data"));
        lsvDataList.setAdapter(adapter);
    }
}