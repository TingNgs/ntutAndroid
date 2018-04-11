package com.paperscissorsstonegame;

import android.os.Bundle;
import android.widget.TextView;
import android.content.res.Resources;
/**
 * Created by NGS on 09/04/2018.
 */

public class ComputerPlay {
    private TextView mTxtComPlay,mTxtResult;
    public String getComputerPlay(int computerPlay){
        // 1 – 剪刀, 2 – 石頭, 3 – 布.
        if(computerPlay == 1){
            return "剪刀";
        }
        else if(computerPlay == 2){
            return "石頭";
        }
        else{
            return "布";
        }
    }
    public String getWinLose(int playerPlay,int computerPlay){
        if(playerPlay == computerPlay){
            return "雙方平手！";
        }else if((playerPlay < computerPlay && !(playerPlay==1 && computerPlay==3)) || (playerPlay == 3 && computerPlay ==1)){
            return "很可惜，你輸了！";
        }else{
            return "恭喜，你贏了！";
        }
    }

}


