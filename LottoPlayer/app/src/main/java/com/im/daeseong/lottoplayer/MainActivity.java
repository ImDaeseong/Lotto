package com.im.daeseong.lottoplayer;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.im.daeseong.lottoplayer.Database.DbHandler;
import com.im.daeseong.lottoplayer.Database.Lotto;
import com.im.daeseong.lottoplayer.Database.LottoTop;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static MainActivity mainActivity;
    public static MainActivity getMainActivity(){
        return  mainActivity;
    }
    private static void setMainActivity(MainActivity mainActivity){
        MainActivity.mainActivity = mainActivity;
    }

    private DbHandler dbHandler;

    private List<Lotto> mLotto_list;
    private List<LottoTop> mLottoTop_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity.setMainActivity(this);

        InitTitleBar();

        setContentView(R.layout.activity_main);

        init();
    }

    private void InitTitleBar(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.statusbar_bg));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void init(){

        dbHandler = DbHandler.getInstance(this);


        //getLottoRowCount
        int nCount = dbHandler.getLottoRowCount();
        Log.e(TAG, "Count:" + nCount);


        //getPartTop
        Log.e(TAG, " PartTop1:" + dbHandler.getPartTop(1));
        Log.e(TAG, " PartTop2:" + dbHandler.getPartTop(2));
        Log.e(TAG, " PartTop3:" + dbHandler.getPartTop(3));
        Log.e(TAG, " PartTop4:" + dbHandler.getPartTop(4));
        Log.e(TAG, " PartTop5:" + dbHandler.getPartTop(5));
        Log.e(TAG, " PartTop6:" + dbHandler.getPartTop(6));


        /*
        //addLotto
        Lotto lotto = new Lotto(826, "2018.09.29", 1, 2, 3, 4, 5, 6, 45);
        dbHandler.addLotto(lotto);
        nCount = dbHandler.getLottoRowCount();
        Log.e(TAG, "Count:" + nCount);


        //deleteLotto
        dbHandler.deleteLotto(826);
        nCount = dbHandler.getLottoRowCount();
        Log.e(TAG, "Count:" + nCount);
        */


        mLotto_list = new ArrayList<>();
        mLotto_list  = dbHandler.getLotto();

        mLottoTop_list = new ArrayList<>();
        mLottoTop_list  = dbHandler.getLottoTop();

        for (int i = 0; i < mLotto_list.size(); i++) {
            Log.e(TAG, "Index:" + mLotto_list.get(i).getrIndex() + " Date:" + mLotto_list.get(i).getDate() + " Part1:" + mLotto_list.get(i).getPart1() + " Part2:" + mLotto_list.get(i).getPart2() + " Part3:" + mLotto_list.get(i).getPart3() + " Part4:" + mLotto_list.get(i).getPart4()  + " Part5:" + mLotto_list.get(i).getPart5() + " Part6:" + mLotto_list.get(i).getPart6() + " Bonus:" + mLotto_list.get(i).getBonus() + "\r\n");
        }

        for (int i = 0; i < mLottoTop_list.size(); i++) {
            Log.e(TAG, "Number:" + mLottoTop_list.get(i).getNumber() + " Count:" + mLottoTop_list.get(i).getCount() + "\r\n");
        }

    }

}
