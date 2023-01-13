package com.im.daeseong.lottoplayer.Util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.im.daeseong.lottoplayer.R;

public class FloatingTextView {

    private View floatingview, cLClose;
    private TextView tv1, tv2;
    private Button btnClose;

    public FloatingTextView(ViewGroup viewGroup){

        floatingview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.floating_layout, viewGroup, false);

        tv1 = (TextView)floatingview.findViewById(R.id.tv1);
        tv2 = (TextView)floatingview.findViewById(R.id.tv2);

        cLClose = (View)floatingview.findViewById(R.id.cLClose);
        cLClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                floatingview.setVisibility(View.GONE);
            }
        });
    }

    public View getFloatingview(){
        return floatingview;
    }

    public void setText1(String sText, int color){
        tv1.setText(sText);
        tv1.setTextColor(color);
    }

    public void setText2(String sText, int color){
        tv2.setText(sText);
        tv2.setTextColor(color);
    }
}
