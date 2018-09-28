package com.im.daeseong.lottoplayer;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class LottoApplication extends Application {

    private static final String TAG = LottoApplication.class.getSimpleName();

    private static LottoApplication mInstance;
    public static synchronized LottoApplication getInstance() {
        return mInstance;
    }

    private static Context mContext;
    public static Context getAppContext(){
        return mContext;
    }

    public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        mContext = getApplicationContext();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
}
