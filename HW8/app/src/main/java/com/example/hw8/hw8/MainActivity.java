package com.example.hw8.hw8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;

public class MainActivity extends AppCompatActivity {

    private Spinner spnItem;
    private Button btnInput, btnRecord;
    private EditText edtDate, edtCost;
    private DatePicker datePicker;
    private ArrayList<String> data;
    private Formatter stringFormatter;
    private StringBuilder stringBuilder;
    private int inputCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spnItem = (Spinner)findViewById(R.id.spn_item);
        btnInput = (Button) findViewById(R.id.btn_input);
        btnRecord = (Button) findViewById(R.id.btn_record);
        edtDate = (EditText) findViewById(R.id.edt_date);
        edtCost = (EditText) findViewById(R.id.edt_cost);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        stringBuilder = new StringBuilder();
        stringFormatter = new Formatter(stringBuilder);
        data=new ArrayList();
        inputCount = 0;

        ArrayAdapter itemList=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.item_list));
        spnItem.setAdapter(itemList);

        datePicker.setOnDateChangedListener(datePickerDateChange);
        btnInput.setOnClickListener(btnInputOnClick);
        btnRecord.setOnClickListener(btnRecordOnClick);
    }
    DatePicker.OnDateChangedListener datePickerDateChange = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String tempDate = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
            edtDate.setText(tempDate);
        }
    };
    View.OnClickListener btnInputOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, edtCost.getText().toString(), Toast.LENGTH_SHORT).show();
            stringBuilder.delete(0, stringBuilder.length());
            stringFormatter.format("項目%d  %10s  %10s  %5s", inputCount++, edtDate.getText().toString(), spnItem.getSelectedItem().toString(), edtCost.getText().toString());
            data.add(stringBuilder.toString());
        }
    };
    View.OnClickListener btnRecordOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,DataActivity.class);
            intent.putStringArrayListExtra("data",data);
            startActivity(intent);
        }
    };
}
