package com.daeseong.lottoplayer


import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daeseong.lottoplayer.Database.LottoTop
import com.daeseong.lottoplayer.Util.Lottoutil.getLottoColor
import com.daeseong.lottoplayer.Util.RoundView


class LottoTopRecyclerAdapter(var context: Context, private var LottoTop_list: List<LottoTop>) :  RecyclerView.Adapter<LottoTopRecyclerAdapter.ViewHolder>() {

    private val tag = LottoRecyclerAdapter::class.java.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v: View = LayoutInflater.from(context).inflate(R.layout.lotto_top_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val stv1 = String.format("%s", LottoTop_list[position].number)
        holder.tv1.setTitleText(stv1)
        holder.tv1.setBackgroundColor(getLottoColor(LottoTop_list[position].number))
        val stv2 = String.format("<font color=\"#ff9900\">%d</font><font color=\"#000000\"> 회 당첨 되었습니다.</font>", LottoTop_list[position].count)
        holder.tv2.text = Html.fromHtml(stv2)
    }

    override fun getItemCount(): Int {
        return LottoTop_list.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv1 : RoundView = view.findViewById(R.id.tv1)
        val tv2: TextView = view.findViewById(R.id.tv2)
    }

}