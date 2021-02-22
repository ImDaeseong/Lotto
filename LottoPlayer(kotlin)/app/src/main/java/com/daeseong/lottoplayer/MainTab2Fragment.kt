package com.daeseong.lottoplayer


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daeseong.lottoplayer.Database.LottoTop


class MainTab2Fragment : Fragment() {

    companion object {
        private val tag = MainTab2Fragment::class.java.simpleName
    }

    private var mContext: Context? = null
    private var MainView: View? = null
    private var recyclerView: RecyclerView? = null
    private var lottoTopRecyclerAdapter: LottoTopRecyclerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mContext = container!!.context
        return inflater.inflate(R.layout.fragment_main_tab2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MainView = view
        recyclerView = view.findViewById<View>(R.id.recycler_view) as RecyclerView
        lottoTopRecyclerAdapter = LottoTopRecyclerAdapter(mContext!!, (activity as MainActivity?)!!.getLottoTop() as List<LottoTop>)
        recyclerView!!.layoutManager = LinearLayoutManager(mContext)
        recyclerView!!.adapter = lottoTopRecyclerAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}