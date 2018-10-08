package com.im.daeseong.lottoplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.im.daeseong.lottoplayer.Database.LottoTop;
import com.im.daeseong.lottoplayer.Util.Lottoutil;
import com.im.daeseong.lottoplayer.Util.RoundView;

import java.util.List;

public class LottoTopRecyclerAdapter extends RecyclerView.Adapter<LottoTopRecyclerAdapter.ViewHolder> {

    private static final String TAG = LottoRecyclerAdapter.class.getSimpleName();

    private Context context;
    private List<LottoTop> LottoTop_list;

    public LottoTopRecyclerAdapter(Context context, List<LottoTop> LottoTop_list){
        this.context = context;
        this.LottoTop_list = LottoTop_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lotto_top_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //String stv1 = String.format("<font color=\"#000000\">숫자 </font><font color=\"#ff9900\">%d</font><font color=\"#000000\"> 는</font>", LottoTop_list.get(position).getNumber());
        //holder.tv1.setText(Html.fromHtml(stv1));
        String stv1 = String.format("%s", LottoTop_list.get(position).getNumber());
        holder.tv1.setTitleText(stv1);//holder.tv1.setText(stv1);
        holder.tv1.setBackgroundColor(Lottoutil.getLottoColor(LottoTop_list.get(position).getNumber()));//holder.tv1.setTextColor(Lottoutil.getLottoColor(lottoList.get(position).getPart1()));

        String stv2 = String.format("<font color=\"#ff9900\">%d</font><font color=\"#000000\"> 회 당첨 되었습니다.</font>", LottoTop_list.get(position).getCount());
        holder.tv2.setText(Html.fromHtml(stv2));
    }

    @Override
    public int getItemCount() {
        return LottoTop_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final RoundView tv1;//public final TextView tv1;
        public final TextView tv2;

        public ViewHolder(View view){
            super(view);

            tv1 = (RoundView)view.findViewById(R.id.tv1);//(TextView)view.findViewById(R.id.tv1);
            tv2 = (TextView)view.findViewById(R.id.tv2);
        }
    }

}



