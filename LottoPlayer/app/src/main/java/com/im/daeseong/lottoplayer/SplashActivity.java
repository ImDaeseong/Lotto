package com.im.daeseong.lottoplayer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.im.daeseong.lottoplayer.Database.CopyDBfile;
import com.im.daeseong.lottoplayer.Util.RecycleUtil;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    private Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InitTitleBar();
        setContentView(R.layout.activity_splash);
        init();
        InitData();
    }

    private void init(){

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        SplashActivity.this.finish();
                        break;
                }
            }
        };
    }

    private void InitData(){

        try{

            boolean bCopy = new CopyDBTask().execute(SplashActivity.this).get();

        }catch (Exception e){
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try{
                    Message msg = handler.obtainMessage();
                    msg.what = 0;
                    handler.sendMessage(msg);
                }catch (Exception e){
                }
            }
        }, 1000);
    }

    //타이틀바 숨기기/가로보기 고정/풀스크린
    private void InitTitleBar(){

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //백버튼 기능 막음
        return;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {

            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
                handler = null;
            }

            RecycleUtil.recursiveRecycle(getWindow().getDecorView());
            System.gc();

        }catch (Exception e){
        }
    }

    private static class CopyDBTask extends AsyncTask<Context, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Context... params) {
            try {
                new CopyDBfile(params[0]);
                return true;

            } catch (Exception e) {
            }
            return false;
        }
    }
}
