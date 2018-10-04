package com.im.daeseong.lottoplayer;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;

import com.im.daeseong.lottoplayer.Database.DbHandler;
import com.im.daeseong.lottoplayer.Database.Lotto;
import com.im.daeseong.lottoplayer.Database.LottoTop;
import com.im.daeseong.lottoplayer.Util.FloatingTextView;
import com.im.daeseong.lottoplayer.Util.Lottoutil;
import com.im.daeseong.lottoplayer.Util.RecycleUtil;

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

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LottoAdapter lottoAdapter;

    private FloatingTextView floatingTextView;

    private DbHandler dbHandler;
    private List<Lotto> mLotto_list;
    private List<LottoTop> mLottoTop_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity.setMainActivity(this);

        InitTitleBar();

        setContentView(R.layout.activity_main);

        InitData();

        init();

        InitFloating();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {

            mLotto_list.clear();
            mLottoTop_list.clear();

            RecycleUtil.recursiveRecycle(getWindow().getDecorView());
            System.gc();

        }catch (Exception e){
        }
    }

    private void InitData(){

        dbHandler = DbHandler.getInstance(this);

        mLotto_list = new ArrayList<>();
        mLotto_list  = dbHandler.getLotto();

        mLottoTop_list = new ArrayList<>();
        mLottoTop_list  = dbHandler.getLottoTop();

        /*
        String [] sArray =  new String[9];
        sArray = dbHandler.getData(826);
        if(sArray[0] != null) {
            Log.e(TAG, "rIndex:" + sArray[0]);
            Log.e(TAG, "Date:" + sArray[1]);
            Log.e(TAG, "Part1:" + sArray[2]);
            Log.e(TAG, "Part2:" + sArray[3]);
            Log.e(TAG, "Part3:" + sArray[4]);
            Log.e(TAG, "Part4:" + sArray[5]);
            Log.e(TAG, "Part5:" + sArray[6]);
            Log.e(TAG, "Part6:" + sArray[7]);
            Log.e(TAG, "Bonus:" + sArray[8]);
        }

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
        */
    }

    private void InitTitleBar(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.statusbar_bg));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void InitFloating(){

        Display display = getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        int screenLength = size.y;

        ViewGroup rootView = (ViewGroup)findViewById(R.id.main1);
        floatingTextView = new FloatingTextView(rootView);
        floatingTextView.getFloatingview().setVisibility(View.VISIBLE);

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) floatingTextView.getFloatingview().getLayoutParams();
        layoutParams.setMargins(0, (screenLength - Lottoutil.dip2px(this, 60)),0,0);
        floatingTextView.getFloatingview().setLayoutParams(layoutParams);
        rootView.addView(floatingTextView.getFloatingview());

        setFloating();
    }

    private void setFloating(){
        try{

            floatingTextView.setText1("순차 번호별 누적횟수", Color.parseColor("#ff9900"));

            String sMsg2 = String.format("%d %d %d %d %d %d", dbHandler.getPartTop(1),dbHandler.getPartTop(2), dbHandler.getPartTop(3),dbHandler.getPartTop(4),dbHandler.getPartTop(5), dbHandler.getPartTop(6));
            floatingTextView.setText2(sMsg2, Color.parseColor("#ff9900"));

        }catch (Exception e){
        }
    }

    private void init(){

        //toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setViewPager(viewPager);
        }

        //tab
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setViewPager(ViewPager viewPager) {
        lottoAdapter = new LottoAdapter(getSupportFragmentManager());
        lottoAdapter.addFragment(new MainTab1Fragment(), "당첨번호");
        lottoAdapter.addFragment(new MainTab2Fragment(), "누적횟수");
        lottoAdapter.addFragment(new MainTab3Fragment(), "등록");
        lottoAdapter.addFragment(new MainTab4Fragment(), "나눔로또");
        viewPager.setAdapter(lottoAdapter);
    }

    public List<Lotto> getLotto() {
        return mLotto_list;
    }

    public List<LottoTop> getLottoTop() {
        return mLottoTop_list;
    }

    public void addLotto(int rIndex, String Date, int Part1, int Part2, int Part3, int Part4, int Part5, int Part6, int Bonus){

        try {
            if (!dbHandler.isExistData(rIndex)) {
                Lotto lotto = new Lotto(rIndex, Date, Part1, Part2, Part3, Part4, Part5, Part6, Bonus);
                dbHandler.addLotto(lotto);
            }
        }catch (Exception e){
        }
    }

    public void deleteLotto(int rIndex){
        try {
            if (dbHandler.isExistData(rIndex)) {
                dbHandler.deleteLotto(rIndex);
            }
        }catch (Exception e){
        }
    }

}
