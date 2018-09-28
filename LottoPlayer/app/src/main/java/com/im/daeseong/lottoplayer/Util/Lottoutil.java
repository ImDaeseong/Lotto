package com.im.daeseong.lottoplayer.Util;

import android.graphics.Color;

public class Lottoutil {

    public static final int getLottoColor(int number) {
        if (0 < number && 10 >= number) {
            return Color.YELLOW;
        } else if (10 < number && 20 >= number) {
            return Color.BLUE;
        } else if (20 < number && 30 >= number) {
            return Color.RED;
        } else if (30 < number && 40 >= number) {
            return Color.BLACK;
        } else if (40  < number && 45 >= number) {
            return Color.GREEN;
        } else {
            return Color.WHITE;
        }
    }
}
