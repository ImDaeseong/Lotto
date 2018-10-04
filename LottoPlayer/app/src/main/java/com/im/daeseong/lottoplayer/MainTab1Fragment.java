package com.im.daeseong.lottoplayer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainTab1Fragment extends Fragment {

    private static final String TAG = MainTab1Fragment.class.getSimpleName();

    private Context mContext;
    private View MainView;

    private RecyclerView recyclerView;
    private LottoRecyclerAdapter lottoRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = container.getContext();
        return inflater.inflate(R.layout.fragment_main_tab1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainView = view;

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        lottoRecyclerAdapter = new LottoRecyclerAdapter(mContext, ((MainActivity)getActivity()).getLotto());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(lottoRecyclerAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
