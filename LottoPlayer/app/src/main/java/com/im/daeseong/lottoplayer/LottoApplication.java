package com.im.daeseong.lottoplayer;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    private static Toast toast;
    public static void Toast(Context context, String sMsg, boolean bLengthLong) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.toast_layout, null);

        TextView tvtoast = view.findViewById(R.id.tvtoast);

        //최대 1줄까지
        tvtoast.setMaxLines(1);
        tvtoast.setTextColor(Color.parseColor("#000000"));
        tvtoast.setText(sMsg);

        toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM, 0, 0);//toast.setGravity(Gravity.CENTER, 0, 0);

        if(bLengthLong) {
            toast.setDuration(Toast.LENGTH_LONG);
        }else {
            toast.setDuration(Toast.LENGTH_SHORT);
        }

        toast.setView(view);
        toast.show();
    }

    public static void Toastcancel(){
        try {
            toast.cancel();
        }catch (Exception e){
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
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
