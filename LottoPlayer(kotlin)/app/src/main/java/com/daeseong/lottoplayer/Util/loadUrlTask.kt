package com.daeseong.lottoplayer.Util

import android.os.AsyncTask
import android.os.SystemClock
import android.webkit.WebView


class loadUrlTask(private var wvWebView: WebView) : AsyncTask<String?, Void?, String>() {

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: String?): String? {
        SystemClock.sleep(1000)
        return params[0]
    }

    override fun onPostExecute(result: String) {
        wvWebView.loadUrl(result)
    }
}