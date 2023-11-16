package com.daeseong.lottoplayer.Util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daeseong.lottoplayer.R
import kotlinx.android.synthetic.main.floating_layout.view.*

class FloatingTextView(viewGroup: ViewGroup) {

    private val floatingView: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.floating_layout, viewGroup, false)

    init {
        floatingView.cLClose.setOnClickListener {
            floatingView.visibility = View.GONE
        }
    }

    fun getFloatingView(): View? {
        return floatingView
    }

    fun setText1(sText: String?, color: Int) {
        floatingView.tv1.text = sText
        floatingView.tv1.setTextColor(color)
    }

    fun setText2(sText: String?, color: Int) {
        floatingView.tv2.text = sText
        floatingView.tv2.setTextColor(color)
    }
}
