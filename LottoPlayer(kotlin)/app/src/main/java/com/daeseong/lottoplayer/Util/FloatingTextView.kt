package com.daeseong.lottoplayer.Util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.daeseong.lottoplayer.R

class FloatingTextView(viewGroup: ViewGroup) {

    private val floatingView: View = LayoutInflater.from(viewGroup.context)
        .inflate(R.layout.floating_layout, viewGroup, false)

    private val cLClose: ConstraintLayout = floatingView.findViewById(R.id.cLClose)
    private val tv1: TextView = floatingView.findViewById(R.id.tv1)
    private val tv2: TextView = floatingView.findViewById(R.id.tv2)

    init {
        cLClose.setOnClickListener {
            floatingView.visibility = View.GONE
        }
    }

    fun getFloatingView(): View = floatingView

    fun setText1(text: String, color: Int) {
        tv1.apply {
            this.text = text
            setTextColor(color)
        }
    }

    fun setText2(text: String, color: Int) {
        tv2.apply {
            this.text = text
            setTextColor(color)
        }
    }
}