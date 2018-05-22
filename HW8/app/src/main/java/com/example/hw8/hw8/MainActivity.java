package com.example.hw8.hw8;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;

public class MainActivity extends AppCompatActivity {
    private static final int MENU_MUSIC = Menu.FIRST,
            MENU_PLAY_MUSIC = Menu.FIRST + 1,
            MENU_STOP_PLAYING_MUSIC = Menu.FIRST + 2,
            MENU_ABOUT = Menu.FIRST + 3,
            MENU_EXIT = Menu.FIRST + 4;
    private Spinner spnItem;
    private Button btnInput, btnRecord;
    private EditText edtDate, edtCost;
    private DatePicker datePicker;
    private ArrayList<String> data;
    private Formatter stringFormatter;
    private StringBuilder stringBuilder;
    private int inputCount;
    private LinearLayout mLinearLayout;

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
        mLinearLayout = (LinearLayout) findViewById(R.id.LinearView);
        registerForContextMenu(mLinearLayout);
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

    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu subMenu = menu.addSubMenu(0, MENU_MUSIC, 0, "背景音樂")
                .setIcon(android.R.drawable.ic_media_ff);
        subMenu.add(0, MENU_PLAY_MUSIC, 0, "播放背景音樂");
        subMenu.add(0, MENU_STOP_PLAYING_MUSIC, 1, "停止播放背景音樂");
        menu.add(0, MENU_ABOUT, 1, "關於這個程式...")
                .setIcon(android.R.drawable.ic_dialog_info);
        menu.add(0, MENU_EXIT, 2, "結束")
                .setIcon(android.R.drawable.ic_menu_close_clear_cancel);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_PLAY_MUSIC:
                Intent it = new Intent(MainActivity.this, MediaPlayService.class);
                startService(it);
                return true;
            case MENU_STOP_PLAYING_MUSIC:
                it = new Intent(MainActivity.this, MediaPlayService.class);
                stopService(it);
                return true;
            case MENU_ABOUT:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("關於這個程式")
                        .setMessage("選單範例程式")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.star_big_on)
                        .setPositiveButton("確定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO Auto-generated method stub
                                    }
                                })
                        .show();

                return true;
            case MENU_EXIT:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v == mLinearLayout) {
            if (menu.size() == 0) {
                SubMenu subMenu = menu.addSubMenu(0, MENU_MUSIC, 0, "背景音樂");
                subMenu.add(0, MENU_PLAY_MUSIC, 0, "播放背景音樂");
                subMenu.add(0, MENU_STOP_PLAYING_MUSIC, 1, "停止播放背景音樂");
                menu.add(0, MENU_ABOUT, 1, "關於這個程式...");
                menu.add(0, MENU_EXIT, 2, "結束");
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        onOptionsItemSelected(item);
        return super.onContextItemSelected(item);
    }
}
