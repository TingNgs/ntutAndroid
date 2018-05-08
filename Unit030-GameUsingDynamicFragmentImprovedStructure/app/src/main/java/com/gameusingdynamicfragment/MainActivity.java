package com.gameusingdynamicfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements MainFragment.CallbackInterface {

    private final static String TAG = "Result";
    private int mTagCount = 0;
    public MainFragment mainFragment;
    public Fragment fragResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragMain);
    }

    @Override
    public void showGameResult(int iCountSet, int iCountPlayerWin, int iCountComWin, int iCountDraw) {
        Intent intent = new Intent(this, GameResultActivity.class);
        intent.putExtra("CountSet", iCountSet);
        intent.putExtra("CountPlayerWin", iCountPlayerWin);
        intent.putExtra("CountComWin", iCountComWin);
        intent.putExtra("CountDraw", iCountDraw);
        startActivity(intent);
    }

}
