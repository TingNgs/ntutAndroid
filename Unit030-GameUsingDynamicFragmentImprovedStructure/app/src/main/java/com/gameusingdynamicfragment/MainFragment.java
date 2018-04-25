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

    public enum GameResultType {
        TYPE_1, TYPE_2, HIDE
    }

    // 所屬的 Activity 必須實作以下介面中的callback方法
    public interface CallbackInterface {
        public void updateGameResult(int iCountSet,
                                     int iCountPlayerWin,
                                     int iCountComWin,
                                     int iCountDraw);
        public void enableGameResult(GameResultType type);
    };

    private CallbackInterface mCallback;
    private EditText mEdtCountSet,mEdtCountPlayerWin,mEdtCountComWin,mEdtCountDraw;
    private ImageView mImgViewDice;
    private TextView mTxtResult;
/*
    public EditText mEdtCountSet,
                    mEdtCountPlayerWin,
                    mEdtCountComWin,
                    mEdtCountDraw;
*/

    // 新增統計遊戲局數和輸贏的變數
    private int miCountSet = 0,
                miCountPlayerWin = 0,
                miCountComWin = 0,
                miCountDraw = 0;

    private boolean isDiceRolling = false;

    private Button mBtnShowResult1,
                    mBtnShowResult2,
                    mBtnHiddenResult;

    private boolean mbShowResult = false;

//    private final static String TAG = "Result";
//    private int mTagCount = 0;

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

        // 必須先呼叫getView()取得程式畫面物件，然後才能呼叫它的
        // findViewById()取得介面物件
        mTxtResult = (TextView) getView().findViewById(R.id.txtResult);
        mImgViewDice = (ImageView) getView().findViewById(R.id.imgViewDice);
        mImgViewDice.setImageResource(R.drawable.dice01);
        // 以下介面元件是在另一個Fragment中，因此必須呼叫所屬的Activity
        // 才能取得這些介面元件
        /*mEdtCountSet = (EditText) getActivity().findViewById(R.id.edtCountSet);
        mEdtCountPlayerWin = (EditText) getActivity().findViewById(R.id.edtCountPlayerWin);
        mEdtCountComWin = (EditText) getActivity().findViewById(R.id.edtCountComWin);
        mEdtCountDraw = (EditText) getActivity().findViewById(R.id.edtCountDraw);*/

        mBtnShowResult1 = (Button) getView().findViewById(R.id.btnShowResult1);
        mBtnShowResult2 = (Button) getView().findViewById(R.id.btnShowResult2);
        mBtnHiddenResult = (Button) getView().findViewById(R.id.btnHiddenResult);

        mBtnShowResult1.setOnClickListener(btnShowResult1OnClick);
        mBtnShowResult2.setOnClickListener(btnShowResult2OnClick);
        mBtnHiddenResult.setOnClickListener(btnHiddenResultOnClick);
        mImgViewDice.setOnClickListener(mImgViewDoceOnClick);
    }
    private View.OnClickListener mImgViewDoceOnClick = new View.OnClickListener() {
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
        mCallback.updateGameResult(miCountSet,miCountPlayerWin,miCountComWin,miCountDraw);
    }
    public void upDate(){
        mCallback.updateGameResult(miCountSet,miCountPlayerWin,miCountComWin,miCountDraw);
    }
    private View.OnClickListener btnShowResult1OnClick = new View.OnClickListener() {
        public void onClick(View v) {
            mCallback.enableGameResult(GameResultType.TYPE_1);
            mCallback.updateGameResult(miCountSet,miCountPlayerWin,miCountComWin,miCountDraw);
        }
    };

    private View.OnClickListener btnShowResult2OnClick = new View.OnClickListener() {
        public void onClick(View v) {
            mCallback.enableGameResult(GameResultType.TYPE_2);
            mCallback.updateGameResult(miCountSet,miCountPlayerWin,miCountComWin,miCountDraw);
        }
    };

    private View.OnClickListener btnHiddenResultOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            mCallback.enableGameResult(GameResultType.HIDE);
        }
    };

}
