package com.gameusingdynamicfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by NGS on 09/05/2018.
 */

public class GameResultActivity extends AppCompatActivity {
    private EditText edtTotalGame, edtPlayerWin, edtComWin, edtDraw;
    private Button btnGoBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_result);
        edtTotalGame = (EditText) findViewById(R.id.edtTotalGame);
        edtPlayerWin = (EditText) findViewById(R.id.edtPlayerWin);
        edtComWin = (EditText) findViewById(R.id.edtComWin);
        edtDraw = (EditText) findViewById(R.id.edtDraw);
        btnGoBack = (Button) findViewById(R.id.btnBack);

        Intent intent = getIntent();
        edtTotalGame.setText(String.valueOf(intent.getIntExtra("CountSet",0)));
        edtPlayerWin.setText(String.valueOf(intent.getIntExtra("CountPlayerWin",0)));
        edtComWin.setText(String.valueOf(intent.getIntExtra("CountComWin",0)));
        edtDraw.setText(String.valueOf(intent.getIntExtra("CountDraw",0)));

        btnGoBack.setOnClickListener(goBack);
    }

    private View.OnClickListener goBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
