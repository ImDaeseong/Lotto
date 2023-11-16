package com.daeseong.lottoplayer

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daeseong.lottoplayer.Database.Lotto

class MainTab1Fragment : Fragment() {

    companion object {
        private val tag = MainTab1Fragment::class.java.simpleName
    }

    private lateinit var mContext: Context
    private lateinit var MainView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var lottoRecyclerAdapter: LottoRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContext = container!!.context
        return inflater.inflate(R.layout.fragment_main_tab1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MainView = view
        recyclerView = view.findViewById(R.id.recycler_view)
        lottoRecyclerAdapter = LottoRecyclerAdapter(mContext, (activity as MainActivity?)!!.getLotto() as List<Lotto>)
        recyclerView.layoutManager = LinearLayoutManager(mContext)
        recyclerView.adapter = lottoRecyclerAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
