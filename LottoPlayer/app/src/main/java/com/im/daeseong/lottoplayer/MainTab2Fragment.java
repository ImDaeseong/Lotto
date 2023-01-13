package com.im.daeseong.lottoplayer;

import android.os.Bundle;
import androidx.annotation.Nullable;//import android.support.annotation.Nullable;
import androidx.fragment.app.Fragment;//import android.support.v4.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;//import android.support.v7.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;

public class MainTab2Fragment extends Fragment {

    private static final String TAG = MainTab2Fragment.class.getSimpleName();

    private Context mContext;
    private View MainView;

    private RecyclerView recyclerView;
    private LottoTopRecyclerAdapter lottoTopRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = container.getContext();
        return inflater.inflate(R.layout.fragment_main_tab2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainView = view;

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        lottoTopRecyclerAdapter = new LottoTopRecyclerAdapter(mContext, ((MainActivity)getActivity()).getLottoTop());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(lottoTopRecyclerAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
