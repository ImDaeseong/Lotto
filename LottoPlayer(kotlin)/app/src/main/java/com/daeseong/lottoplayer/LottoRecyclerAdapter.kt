package com.daeseong.lottoplayer

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daeseong.lottoplayer.Database.Lotto
import com.daeseong.lottoplayer.Util.Lottoutil.getLottoColor
import com.daeseong.lottoplayer.Util.RoundView

class LottoRecyclerAdapter(var context: Context, private var lottoList: List<Lotto>) : RecyclerView.Adapter<LottoRecyclerAdapter.ViewHolder>() {

    private val tag = LottoRecyclerAdapter::class.java.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v: View = LayoutInflater.from(context).inflate(R.layout.lotto_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val stvDesc = String.format("<font color=\"#ff9900\">%d</font><font color=\"#000000\"> 회 당첨결과(%s)</font>", lottoList[position].getrIndex(), lottoList[position].getDate())
        holder.tvDesc.text = Html.fromHtml(stvDesc)

        val stv1 = String.format("%s", lottoList[position].getPart1())
        holder.tv1.setTitleText(stv1)
        holder.tv1.setBackgroundColor(getLottoColor(lottoList[position].getPart1()))

        val stv2 = String.format("%s", lottoList[position].getPart2())
        holder.tv2.setTitleText(stv2)
        holder.tv2.setBackgroundColor(getLottoColor(lottoList[position].getPart2()))

        val stv3 = String.format("%s", lottoList[position].getPart3())
        holder.tv3.setTitleText(stv3)
        holder.tv3.setBackgroundColor(getLottoColor(lottoList[position].getPart3()))

        val stv4 = String.format("%s", lottoList[position].getPart4())
        holder.tv4.setTitleText(stv4)
        holder.tv4.setBackgroundColor(getLottoColor(lottoList[position].getPart4()))

        val stv5 = String.format("%s", lottoList[position].getPart5())
        holder.tv5.setTitleText(stv5)
        holder.tv5.setBackgroundColor(getLottoColor(lottoList[position].getPart5()))

        val stv6 = String.format("%s", lottoList[position].getPart6())
        holder.tv6.setTitleText(stv6)
        holder.tv6.setBackgroundColor(getLottoColor(lottoList[position].getPart6()))
    }

    override fun getItemCount(): Int {
        return lottoList.size
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var tvDesc: TextView = v.findViewById(R.id.tvDesc)
        var tv1 : RoundView = v.findViewById(R.id.tv1)
        var tv2 : RoundView = v.findViewById(R.id.tv2)
        var tv3 : RoundView = v.findViewById(R.id.tv3)
        var tv4 : RoundView = v.findViewById(R.id.tv4)
        var tv5 : RoundView = v.findViewById(R.id.tv5)
        var tv6 : RoundView = v.findViewById(R.id.tv6)
    }

}