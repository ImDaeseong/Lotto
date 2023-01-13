package com.im.daeseong.lottoplayer;

import android.content.Context;
import androidx.annotation.NonNull;//import android.support.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;//import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.im.daeseong.lottoplayer.Database.Lotto;
import com.im.daeseong.lottoplayer.Util.Lottoutil;
import com.im.daeseong.lottoplayer.Util.RoundView;
import java.util.List;

public class LottoRecyclerAdapter extends RecyclerView.Adapter<LottoRecyclerAdapter.ViewHolder> {

    private static final String TAG = LottoRecyclerAdapter.class.getSimpleName();

    private Context context;
    private List<Lotto> lottoList;

    public LottoRecyclerAdapter(Context context, List<Lotto> lottoList){
        this.context = context;
        this.lottoList = lottoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lotto_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String stvDesc = String.format("<font color=\"#ff9900\">%d</font><font color=\"#000000\"> 회 당첨결과(%s)</font>", lottoList.get(position).getrIndex(), lottoList.get(position).getDate());
        holder.tvDesc.setText(Html.fromHtml(stvDesc));

        String stv1 = String.format("%s", lottoList.get(position).getPart1());
        holder.tv1.setTitleText(stv1);//holder.tv1.setText(stv1);
        holder.tv1.setBackgroundColor(Lottoutil.getLottoColor(lottoList.get(position).getPart1()));//holder.tv1.setTextColor(Lottoutil.getLottoColor(lottoList.get(position).getPart1()));

        String stv2 = String.format("%s", lottoList.get(position).getPart2());
        holder.tv2.setTitleText(stv2);//holder.tv2.setText(stv2);
        holder.tv2.setBackgroundColor(Lottoutil.getLottoColor(lottoList.get(position).getPart2()));//holder.tv2.setTextColor(Lottoutil.getLottoColor(lottoList.get(position).getPart2()));

        String stv3 = String.format("%s", lottoList.get(position).getPart3());
        holder.tv3.setTitleText(stv3);//holder.tv3.setText(stv3);
        holder.tv3.setBackgroundColor(Lottoutil.getLottoColor(lottoList.get(position).getPart3()));//holder.tv3.setTextColor(Lottoutil.getLottoColor(lottoList.get(position).getPart3()));

        String stv4 = String.format("%s", lottoList.get(position).getPart4());
        holder.tv4.setTitleText(stv4);//holder.tv4.setText(stv4);
        holder.tv4.setBackgroundColor(Lottoutil.getLottoColor(lottoList.get(position).getPart4()));//holder.tv4.setTextColor(Lottoutil.getLottoColor(lottoList.get(position).getPart4()));

        String stv5 = String.format("%s", lottoList.get(position).getPart5());
        holder.tv5.setTitleText(stv5);//holder.tv5.setText(stv5);
        holder.tv5.setBackgroundColor(Lottoutil.getLottoColor(lottoList.get(position).getPart5()));//holder.tv5.setTextColor(Lottoutil.getLottoColor(lottoList.get(position).getPart5()));

        String stv6 = String.format("%s", lottoList.get(position).getPart6());
        holder.tv6.setTitleText(stv6);//holder.tv6.setText(stv6);
        holder.tv6.setBackgroundColor(Lottoutil.getLottoColor(lottoList.get(position).getPart6()));//holder.tv6.setTextColor(Lottoutil.getLottoColor(lottoList.get(position).getPart6()));
    }

    @Override
    public int getItemCount() {
        return lottoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView tvDesc;
        public final RoundView tv1;//public final TextView tv1;
        public final RoundView tv2;//public final TextView tv2;
        public final RoundView tv3;//public final TextView tv3;
        public final RoundView tv4;//public final TextView tv4;
        public final RoundView tv5;//public final TextView tv5;
        public final RoundView tv6;//public final TextView tv6;

        public ViewHolder(View view){
            super(view);

            tvDesc = (TextView)view.findViewById(R.id.tvDesc);
            tv1 = (RoundView)view.findViewById(R.id.tv1);//tv1 = (TextView)view.findViewById(R.id.tv1);
            tv2 = (RoundView)view.findViewById(R.id.tv2);//tv2 = (TextView)view.findViewById(R.id.tv2);
            tv3 = (RoundView)view.findViewById(R.id.tv3);//tv3 = (TextView)view.findViewById(R.id.tv3);
            tv4 = (RoundView)view.findViewById(R.id.tv4);//tv4 = (TextView)view.findViewById(R.id.tv4);
            tv5 = (RoundView)view.findViewById(R.id.tv5);//tv5 = (TextView)view.findViewById(R.id.tv5);
            tv6 = (RoundView)view.findViewById(R.id.tv6);//tv6 = (TextView)view.findViewById(R.id.tv6);
        }
    }

}