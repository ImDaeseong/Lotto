package com.daeseong.lottoplayer.Util

import android.content.Context
import android.graphics.Color

object Lottoutil {

    fun getLottoColor(number: Int): Int {
        return when (number) {
            in 1..10 -> {
                Color.parseColor("#ffffbb33") // Color.YELLOW
            }
            in 11..20 -> {
                Color.parseColor("#ff0099cc") //Color.BLUE
            }
            in 21..30 -> {
                Color.parseColor("#ffff4444") //Color.RED
            }
            in 31..40 -> {
                Color.parseColor("#80919E") //Color.BLACK
            }
            in 41..45 -> {
                Color.parseColor("#339933") //Color.GREEN
            }
            else -> {
                Color.WHITE
            }
        }
    }

    fun toInt(sInput: String, defValue: Int): Int {
        try {
            return sInput.toInt()
        } catch (e: Exception) {
        }
        return defValue
    }

    fun dip2px(context: Context, dpValue: Float): Int {
        val scale: Float = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun px2dip(context: Context, dpValue: Float): Int {
        val scale: Float = context.resources.displayMetrics.density
        return (dpValue / scale + 0.5f).toInt()
    }
}