package com.im.daeseong.lottoplayer.Util;

import android.content.Context;
import android.graphics.Color;

public class Lottoutil {

    public static final int getLottoColor(int number) {
        if (0 < number && 10 >= number) {
            return Color.parseColor("#ffffbb33");// Color.YELLOW;
        } else if (10 < number && 20 >= number) {
            return Color.parseColor("#ff0099cc");//Color.BLUE;
        } else if (20 < number && 30 >= number) {
            return Color.parseColor("#ffff4444");//Color.RED;
        } else if (30 < number && 40 >= number) {
            return Color.parseColor("#80919E");//Color.BLACK;
        } else if (40  < number && 45 >= number) {
            return Color.parseColor("#339933");//Color.GREEN;
        } else {
            return Color.WHITE;
        }
    }

    public static int toInt(String sInput, int defValue) {
        try {
            return Integer.parseInt(sInput);
        } catch (Exception e) {
        }
        return defValue;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    public static int px2dip(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue / scale + 0.5F);
    }
}
