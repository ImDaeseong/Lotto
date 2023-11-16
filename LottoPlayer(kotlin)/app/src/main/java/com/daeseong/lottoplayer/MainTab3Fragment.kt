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

    private lateinit var mContext: Context
    private lateinit var edDesc: EditText
    private lateinit var edDate: EditText
    private lateinit var et1: EditText
    private lateinit var et2: EditText
    private lateinit var et3: EditText
    private lateinit var et4: EditText
    private lateinit var et5: EditText
    private lateinit var et6: EditText
    private lateinit var et7: EditText
    private lateinit var btnSet: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContext = container!!.context
        return inflater.inflate(R.layout.fragment_main_tab3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edDesc = view.findViewById(R.id.edDesc)
        edDate = view.findViewById(R.id.edDate)
        et1 = view.findViewById(R.id.et1)
        et2 = view.findViewById(R.id.et2)
        et3 = view.findViewById(R.id.et3)
        et4 = view.findViewById(R.id.et4)
        et5 = view.findViewById(R.id.et5)
        et6 = view.findViewById(R.id.et6)
        et7 = view.findViewById(R.id.et7)
        btnSet = view.findViewById(R.id.btnSet)
        btnSet.setOnClickListener { addLottoInfo() }
    }

    private fun addLottoInfo() {

        val sedDesc = edDesc.text.toString()
        if (TextUtils.isEmpty(sedDesc)) {
            LottoApplication.getInstance().showToast("당첨회차 정보를 입력해주세요.", false)
            return
        }

        val sedDate = edDate.text.toString()
        if (TextUtils.isEmpty(sedDate)) {
            LottoApplication.getInstance().showToast("당첨일자 정보를 입력해주세요.", false)
            return
        }

        val set1 = et1.text.toString()
        if (TextUtils.isEmpty(set1)) {
            LottoApplication.getInstance().showToast("당첨번호 1번째 번호를 입력해주세요.", false)
            return
        }

        val set2 = et2.text.toString()
        if (TextUtils.isEmpty(set2)) {
            LottoApplication.getInstance().showToast("당첨번호 2번째 번호를 입력해주세요.", false)
            return
        }

        val set3 = et3.text.toString()
        if (TextUtils.isEmpty(set3)) {
            LottoApplication.getInstance().showToast("당첨번호 3번째 번호를 입력해주세요.", false)
            return
        }

        val set4 = et4.text.toString()
        if (TextUtils.isEmpty(set4)) {
            LottoApplication.getInstance().showToast("당첨번호 4번째 번호를 입력해주세요.", false)
            return
        }

        val set5 = et5.text.toString()
        if (TextUtils.isEmpty(set5)) {
            LottoApplication.getInstance().showToast("당첨번호 5번째 번호를 입력해주세요.", false)
            return
        }

        val set6 = et6.text.toString()
        if (TextUtils.isEmpty(set6)) {
            LottoApplication.getInstance().showToast("당첨번호 6번째 번호를 입력해주세요.", false)
            return
        }

        val set7 = et7.text.toString()
        if (TextUtils.isEmpty(set7)) {
            LottoApplication.getInstance().showToast("보너스 번호를 입력해주세요.", false)
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
        (activity as MainActivity?)!!.addLotto(rIndex, sedDate, nPart1, nPart2, nPart3, nPart4, nPart5, nPart6, nBonus)

        edDesc.setText("")
        edDate.setText("")
        et1.setText("")
        et2.setText("")
        et3.setText("")
        et4.setText("")
        et5.setText("")
        et6.setText("")
        et7.setText("")
        (activity as MainActivity?)!!.reloadLotto()

        LottoApplication.getInstance().showToast("복권번호 등록이 완료되었습니다.", false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
