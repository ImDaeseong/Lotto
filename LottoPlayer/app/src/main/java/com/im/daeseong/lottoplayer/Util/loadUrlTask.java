package com.im.daeseong.lottoplayer.Util;

import android.os.AsyncTask;
import android.webkit.WebView;

public class loadUrlTask extends AsyncTask<String, Void, String>  {

    private final WebView wvWebView;

    public loadUrlTask(WebView wvWebView) {
        this.wvWebView = wvWebView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(String... params) {
        //SystemClock.sleep(1000);
        return params[0];
    }

    @Override
    protected void onPostExecute(String result) {
        wvWebView.loadUrl(result);
    }

}
