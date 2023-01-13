package com.im.daeseong.lottoplayer;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;//import android.support.annotation.Nullable;
import androidx.fragment.app.Fragment;//import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.im.daeseong.lottoplayer.Util.Lottoutil;

public class MainTab3Fragment extends Fragment {

    private static final String TAG = MainTab3Fragment.class.getSimpleName();

    private Context mContext;
    private EditText edDesc, edDate, et1, et2, et3, et4, et5, et6, et7;
    private Button btnSet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = container.getContext();
        return inflater.inflate(R.layout.fragment_main_tab3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edDesc = (EditText)view.findViewById(R.id.edDesc);
        edDate = (EditText)view.findViewById(R.id.edDate);
        et1 = (EditText)view.findViewById(R.id.et1);
        et2 = (EditText)view.findViewById(R.id.et2);
        et3 = (EditText)view.findViewById(R.id.et3);
        et4 = (EditText)view.findViewById(R.id.et4);
        et5 = (EditText)view.findViewById(R.id.et5);
        et6 = (EditText)view.findViewById(R.id.et6);
        et7 = (EditText)view.findViewById(R.id.et7);

        btnSet = (Button)view.findViewById(R.id.btnSet);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLottoInfo();
            }
        });
    }

    private void addLottoInfo(){

        String sedDesc = edDesc.getText().toString();
        if( TextUtils.isEmpty( sedDesc )){
            LottoApplication.getInstance().Toast(mContext, "당첨회차 정보를 입력해주세요.", false);
            return;
        }

        String sedDate = edDate.getText().toString();
        if( TextUtils.isEmpty( sedDate )){
            LottoApplication.getInstance().Toast(mContext, "당첨일자 정보를 입력해주세요.", false);
            return;
        }

        String set1 = et1.getText().toString();
        if( TextUtils.isEmpty( set1 )){
            LottoApplication.getInstance().Toast(mContext, "당첨번호 1번째 번호를 입력해주세요.", false);
            return;
        }

        String set2 = et2.getText().toString();
        if( TextUtils.isEmpty( set2 )){
            LottoApplication.getInstance().Toast(mContext, "당첨번호 2번째 번호를 입력해주세요.", false);
            return;
        }

        String set3 = et3.getText().toString();
        if( TextUtils.isEmpty( set3 )){
            LottoApplication.getInstance().Toast(mContext, "당첨번호 3번째 번호를 입력해주세요.", false);
            return;
        }

        String set4 = et4.getText().toString();
        if( TextUtils.isEmpty( set4 )){
            LottoApplication.getInstance().Toast(mContext, "당첨번호 4번째 번호를 입력해주세요.", false);
            return;
        }

        String set5 = et5.getText().toString();
        if( TextUtils.isEmpty( set5 )){
            LottoApplication.getInstance().Toast(mContext, "당첨번호 5번째 번호를 입력해주세요.", false);
            return;
        }

        String set6 = et6.getText().toString();
        if( TextUtils.isEmpty( set6 )){
            LottoApplication.getInstance().Toast(mContext, "당첨번호 6번째 번호를 입력해주세요.", false);
            return;
        }

        String set7 = et7.getText().toString();
        if( TextUtils.isEmpty( set7 )){
            LottoApplication.getInstance().Toast(mContext, "보너스 번호를 입력해주세요.", false);
            return;
        }

        int rIndex = Lottoutil.toInt(sedDesc, 0);
        String Date = sedDate;
        int Part1 = Lottoutil.toInt(set1, 0);
        int Part2 = Lottoutil.toInt(set2, 0);
        int Part3 = Lottoutil.toInt(set3, 0);
        int Part4 = Lottoutil.toInt(set4, 0);
        int Part5 = Lottoutil.toInt(set5, 0);
        int Part6 = Lottoutil.toInt(set6, 0);
        int Bonus = Lottoutil.toInt(set7, 0);
        ((MainActivity)getActivity()).addLotto(rIndex, Date, Part1, Part2, Part3, Part4, Part5, Part6, Bonus);

        edDesc.setText("");
        edDate.setText("");
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");
        et7.setText("");

        ((MainActivity)getActivity()).reloadLotto();
        LottoApplication.getInstance().Toast(mContext, "복권번호 등록이 완료되었습니다.", false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

}
