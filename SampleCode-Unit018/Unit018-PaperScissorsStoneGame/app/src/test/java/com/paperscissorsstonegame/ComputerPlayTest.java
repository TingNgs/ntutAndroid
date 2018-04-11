package com.paperscissorsstonegame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by NGS on 11/04/2018.
 */

public class ComputerPlayTest {
    private ComputerPlay cp;
    @Before public void setUp(){
        cp = new ComputerPlay();
    }
    @After public void tearDown(){
        cp = null;
    }
    @Test
    public void testGetComputerPlay(){
        assertEquals("剪刀",cp.getComputerPlay(1));
        assertEquals("石頭",cp.getComputerPlay(2));
        assertEquals("布",cp.getComputerPlay(3));
    }

    @Test
    public void testGetWinLose(){
        assertEquals("雙方平手！",cp.getWinLose(1,1));
        assertEquals("很可惜，你輸了！",cp.getWinLose(1,2));
        assertEquals("恭喜，你贏了！",cp.getWinLose(1,3));

        assertEquals("恭喜，你贏了！",cp.getWinLose(2,1));
        assertEquals("雙方平手！",cp.getWinLose(2,2));
        assertEquals("很可惜，你輸了！",cp.getWinLose(2,3));

        assertEquals("很可惜，你輸了！",cp.getWinLose(3,1));
        assertEquals("恭喜，你贏了！",cp.getWinLose(3,2));
        assertEquals("雙方平手！",cp.getWinLose(3,3));
    }
}
