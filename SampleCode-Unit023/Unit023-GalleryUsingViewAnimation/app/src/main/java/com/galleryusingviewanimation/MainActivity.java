package com.galleryusingviewanimation;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory {

    private GridView mGridView;
    private ImageSwitcher mImgSwitcher;

    private Integer[] miImgArr = {
            R.drawable.img01, R.drawable.img02, R.drawable.img03,
            R.drawable.img04, R.drawable.img05, R.drawable.img06,
            R.drawable.img07, R.drawable.img08,R.drawable.img09,
            R.drawable.img10, R.drawable.img11,R.drawable.img12};

    private Integer[] miThumbImgArr = {
            R.drawable.img01th, R.drawable.img02th, R.drawable.img03th,
            R.drawable.img04th, R.drawable.img05th, R.drawable.img06th,
            R.drawable.img07th, R.drawable.img08th ,R.drawable.img09th,
            R.drawable.img10th, R.drawable.img11th,R.drawable.img12th};

    Animation [] animationSetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImgSwitcher = (ImageSwitcher) findViewById(R.id.imgSwitcher);

        mImgSwitcher.setFactory(this);	// 主程式類別必須implements ViewSwitcher.ViewFactory
        mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));

        ImageAdapter imgAdap = new ImageAdapter(this, miThumbImgArr);

        AddAnimation();

        mGridView = (GridView)findViewById(R.id.gridView);
        mGridView.setAdapter(imgAdap);
        //mGridView.setOnItemClickListener(gridViewOnItemClick);
        mGridView.setOnItemClickListener(gridViewOnItemClick_animationSet);
    }

    private void AddAnimation(){
        AlphaAnimation alpha_in = new AlphaAnimation(0f,1f);
        alpha_in.setInterpolator(new LinearInterpolator());
        alpha_in.setDuration(2000);
        AlphaAnimation alpha_out = new AlphaAnimation(1f,0f);
        alpha_out.setInterpolator(new LinearInterpolator());
        alpha_out.setDuration(2000);

        TranslateAnimation trans_in = new TranslateAnimation(0f,0f,-500f,0f);
        trans_in.setInterpolator(new LinearInterpolator());
        trans_in.setDuration(2000);

        TranslateAnimation trans_out = new TranslateAnimation(0f,0f,0f,500f);
        trans_out.setInterpolator(new LinearInterpolator());
        trans_out.setDuration(2000);

        ScaleAnimation scaleIn,scaleOut;
        RotateAnimation rotateIn,rotateOut;
        TranslateAnimation transIn,transOut;

        scaleIn = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleIn.setInterpolator(new LinearInterpolator());
        scaleIn.setStartOffset(2000);
        scaleIn.setDuration(2000);
        rotateIn = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateIn.setInterpolator(new AccelerateDecelerateInterpolator());
        rotateIn.setStartOffset(2000);
        rotateIn.setDuration(2000);
        transIn = new TranslateAnimation(0f, 0f, -500f, 0f);
        transIn.setInterpolator(new LinearInterpolator());
        transIn.setStartOffset(2000);
        transIn.setDuration(2000);

        AnimationSet scale_rotate_in = new AnimationSet(false);
        scale_rotate_in.addAnimation(scaleIn);
        scale_rotate_in.addAnimation(rotateIn);
        AnimationSet scale_rotate_trans_in = new AnimationSet(false);
        scale_rotate_trans_in.addAnimation(scaleIn);
        scale_rotate_trans_in.addAnimation(rotateIn);
        scale_rotate_trans_in.addAnimation(transIn);

        scaleOut = new ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleOut.setInterpolator(new LinearInterpolator());
        scaleOut.setDuration(2000);
        rotateOut = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateOut.setInterpolator(new AccelerateDecelerateInterpolator());
        rotateOut.setDuration(2000);
        transOut = new TranslateAnimation(0f, 0f, 0f, 500f);
        transOut.setInterpolator(new LinearInterpolator());
        transOut.setDuration(2000);

        AnimationSet scale_rotate_out = new AnimationSet(false);
        scale_rotate_out.addAnimation(scaleOut);
        scale_rotate_out.addAnimation(rotateOut);
        AnimationSet scale_rotate_trans_out = new AnimationSet(false);
        scale_rotate_trans_out.addAnimation(scaleOut);
        scale_rotate_trans_out.addAnimation(rotateOut);
        scale_rotate_trans_out.addAnimation(transOut);

        animationSetList = new Animation[]{
                alpha_in,
                alpha_out,
                trans_in,
                trans_out,
                scale_rotate_in,
                scale_rotate_out,
                scale_rotate_trans_in,
                scale_rotate_trans_out
        };
    }

    @Override
    public View makeView() {
        ImageView v = new ImageView(this);
        v.setBackgroundColor(0xFF000000);
        v.setScaleType(ImageView.ScaleType.FIT_CENTER);
        v.setLayoutParams(new ImageSwitcher.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        v.setBackgroundColor(Color.WHITE);
        return v;
    }


    private AdapterView.OnItemClickListener gridViewOnItemClick = new
            AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent,
                                        View v,
                                        int position,
                                        long id) {
                    switch ((int)(Math.random()*4 + 1)) {
                        case 1:
                            mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.alpha_in));
                            mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.alpha_out));
                            break;
                        case 2:
                            mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.trans_in));
                            mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.trans_out));
                            break;
                        case 3:
                            mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.scale_rotate_in));
                            mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.scale_rotate_out));
                            break;
                        case 4:
                            mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.scale_rotate_trans_in));
                            mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.scale_rotate_trans_out));
                            break;
                    }

                    mImgSwitcher.setImageResource(miImgArr[position]);
                }
            };

    private AdapterView.OnItemClickListener gridViewOnItemClick_animationSet = new
            AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int randomAnimation = ((int)Math.floor(Math.random()*4));
                    mImgSwitcher.setInAnimation(animationSetList[randomAnimation*2]);
                    mImgSwitcher.setOutAnimation(animationSetList[(randomAnimation*2)+1]);
                    mImgSwitcher.setImageResource(miImgArr[position]);

                }
            };
}
