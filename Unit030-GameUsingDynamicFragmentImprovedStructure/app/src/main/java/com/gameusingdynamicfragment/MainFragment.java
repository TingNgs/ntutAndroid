package com.gameusingdynamicfragment;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainFragment extends Fragment {

    public interface CallbackInterface {
        public void showGameResult(int iCountSet,
                                     int iCountPlayerWin,
                                     int iCountComWin,
                                     int iCountDraw);
    };

    private CallbackInterface mCallback;
    private ImageView mImgViewDice;
    private TextView mTxtResult;
    private Button btnPlay,btnShowResult;


    // 新增統計遊戲局數和輸贏的變數
    private int miCountSet = 0,
                miCountPlayerWin = 0,
                miCountComWin = 0,
                miCountDraw = 0;

    private boolean isDiceRolling = false;


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (CallbackInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    "must implement GameFragment.CallbackInterface.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 利用inflater物件的inflate()方法取得介面佈局檔，並將最後的結果傳回給系統
       return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mTxtResult = (TextView) getView().findViewById(R.id.txtResult);

        mImgViewDice = (ImageView) getView().findViewById(R.id.imgViewDice);
        mImgViewDice.setImageResource(R.drawable.dice01);

        btnPlay = (Button)getView().findViewById(R.id.btnPlay);
        btnShowResult = (Button)getView().findViewById(R.id.btnShowResult);

        btnPlay.setOnClickListener(playOnClick);
        btnShowResult.setOnClickListener(showResult);
    }
    private View.OnClickListener playOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isDiceRolling) return;
            else  isDiceRolling = true;
            Resources res = getResources();
            final AnimationDrawable animDice = (AnimationDrawable) res.getDrawable(R.drawable.anim_roll_dice);
            mImgViewDice.setImageDrawable(animDice);
            animDice.start();
            Handler hander= new Handler();
            hander.postDelayed(new Runnable() {
                @Override
                public void run() {
                    animDice.stop();
                    isDiceRolling = false;
                    playDice();
                }
            },1000);
        }
    };
    private void playDice(){
        int diceNumber = (int) Math.floor((Math.random()*6));
        int[] diceImages = new int[] { R.drawable.dice01, R.drawable.dice02,
                R.drawable.dice03, R.drawable.dice04,
                R.drawable.dice05, R.drawable.dice06  };
        mImgViewDice.setImageResource(diceImages[diceNumber]);
        if(diceNumber <2){
            mTxtResult.setText(R.string.player_win);
            miCountPlayerWin +=1;
        }else if(diceNumber < 4){
            mTxtResult.setText(R.string.player_draw);
            miCountDraw +=1;
        }else {
            mTxtResult.setText(R.string.player_lose);
            miCountComWin+=1;
        }
        miCountSet+=1;
    }
    private View.OnClickListener showResult = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCallback.showGameResult(miCountSet,miCountPlayerWin,miCountComWin,miCountDraw);
        }
    };


}
