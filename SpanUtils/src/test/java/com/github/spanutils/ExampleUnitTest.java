package com.github.spanutils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void sfd() {
        int temp = 360 % 360;
        System.out.println((360+0  ) % 360);
        System.out.println((360+45 ) % 360);
        System.out.println((360+90 ) % 360);
        System.out.println((360+135) % 360);
        System.out.println((360+180) % 360);
        System.out.println((360+225) % 360);
        System.out.println((360+270) % 360);
        System.out.println((360+316) % 360);
    }
    @Test
    public void sdfd() {
        System.out.println(Math.cos(60));
        System.out.println(Math.cosh(Math.toDegrees(60)));
        System.out.println(Math.cos(Math.toRadians(60)));
    }

}