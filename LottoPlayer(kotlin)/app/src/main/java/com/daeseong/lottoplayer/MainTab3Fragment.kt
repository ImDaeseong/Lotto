package com.daeseong.lottoplayer

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.daeseong.lottoplayer.Util.Lottoutil

class MainTab3Fragment : Fragment() {

    companion object {
        private val tag = MainTab3Fragment::class.java.simpleName
    }

    private var mContext: Context? = null
    private var edDesc: EditText? = null
    private var edDate: EditText? = null
    private var et1: EditText? = null
    private var et2: EditText? = null
    private var et3: EditText? = null
    private var et4: EditText? = null
    private var et5: EditText? = null
    private var et6: EditText? = null
    private var et7: EditText? = null
    private var btnSet: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mContext = container!!.context
        return inflater.inflate(R.layout.fragment_main_tab3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edDesc = view.findViewById<View>(R.id.edDesc) as EditText
        edDate = view.findViewById<View>(R.id.edDate) as EditText
        et1 = view.findViewById<View>(R.id.et1) as EditText
        et2 = view.findViewById<View>(R.id.et2) as EditText
        et3 = view.findViewById<View>(R.id.et3) as EditText
        et4 = view.findViewById<View>(R.id.et4) as EditText
        et5 = view.findViewById<View>(R.id.et5) as EditText
        et6 = view.findViewById<View>(R.id.et6) as EditText
        et7 = view.findViewById<View>(R.id.et7) as EditText
        btnSet = view.findViewById<View>(R.id.btnSet) as Button
        btnSet!!.setOnClickListener { addLottoInfo() }
    }

    private fun addLottoInfo() {

        val sedDesc = edDesc!!.text.toString()
        if (TextUtils.isEmpty(sedDesc)) {
            LottoApplication.getInstance().Toast(mContext!!, "당첨회차 정보를 입력해주세요.", false)
            return
        }

        val sedDate = edDate!!.text.toString()
        if (TextUtils.isEmpty(sedDate)) {
            LottoApplication.getInstance().Toast(mContext!!, "당첨일자 정보를 입력해주세요.", false)
            return
        }

        val set1 = et1!!.text.toString()
        if (TextUtils.isEmpty(set1)) {
            LottoApplication.getInstance().Toast(mContext!!, "당첨번호 1번째 번호를 입력해주세요.", false)
            return
        }

        val set2 = et2!!.text.toString()
        if (TextUtils.isEmpty(set2)) {
            LottoApplication.getInstance().Toast(mContext!!, "당첨번호 2번째 번호를 입력해주세요.", false)
            return
        }

        val set3 = et3!!.text.toString()
        if (TextUtils.isEmpty(set3)) {
            LottoApplication.getInstance().Toast(mContext!!, "당첨번호 3번째 번호를 입력해주세요.", false)
            return
        }

        val set4 = et4!!.text.toString()
        if (TextUtils.isEmpty(set4)) {
            LottoApplication.getInstance().Toast(mContext!!, "당첨번호 4번째 번호를 입력해주세요.", false)
            return
        }

        val set5 = et5!!.text.toString()
        if (TextUtils.isEmpty(set5)) {
            LottoApplication.getInstance().Toast(mContext!!, "당첨번호 5번째 번호를 입력해주세요.", false)
            return
        }

        val set6 = et6!!.text.toString()
        if (TextUtils.isEmpty(set6)) {
            LottoApplication.getInstance().Toast(mContext!!, "당첨번호 6번째 번호를 입력해주세요.", false)
            return
        }

        val set7 = et7!!.text.toString()
        if (TextUtils.isEmpty(set7)) {
            LottoApplication.getInstance().Toast(mContext!!, "보너스 번호를 입력해주세요.", false)
            return
        }

        val rIndex = Lottoutil.toInt(sedDesc, 0)
        val nPart1 = Lottoutil.toInt(set1, 0)
        val nPart2 = Lottoutil.toInt(set2, 0)
        val nPart3 = Lottoutil.toInt(set3, 0)
        val nPart4 = Lottoutil.toInt(set4, 0)
        val nPart5 = Lottoutil.toInt(set5, 0)
        val nPart6 = Lottoutil.toInt(set6, 0)
        val nBonus = Lottoutil.toInt(set7, 0)
        (activity as MainActivity?)!!.addLotto( rIndex, sedDate, nPart1, nPart2, nPart3, nPart4, nPart5, nPart6, nBonus)

        edDesc!!.setText("")
        edDate!!.setText("")
        et1!!.setText("")
        et2!!.setText("")
        et3!!.setText("")
        et4!!.setText("")
        et5!!.setText("")
        et6!!.setText("")
        et7!!.setText("")
        (activity as MainActivity?)!!.reloadLotto()

        LottoApplication.getInstance().Toast(mContext!!, "복권번호 등록이 완료되었습니다.", false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}
