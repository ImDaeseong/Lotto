package com.daeseong.lottoplayer

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daeseong.lottoplayer.Database.LottoTop
import com.daeseong.lottoplayer.Util.Lottoutil.getLottoColor
import com.daeseong.lottoplayer.Util.RoundView

class LottoTopRecyclerAdapter(private val context: Context, private val lottoTopList: List<LottoTop>) : RecyclerView.Adapter<LottoTopRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv1: RoundView = view.findViewById(R.id.tv1)
        val tv2: TextView = view.findViewById(R.id.tv2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.lotto_top_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = lottoTopList[position]

        holder.tv1.apply {
            setTitleText(currentItem.number.toString())
            setBackgroundColor(getLottoColor(currentItem.number))
        }

        val stv2 = String.format("<font color=\"#ff9900\">%d</font><font color=\"#000000\"> 회 당첨 되었습니다.</font>", currentItem.count)

        holder.tv2.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(stv2, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(stv2)
        }
    }

    override fun getItemCount(): Int = lottoTopList.size
}
