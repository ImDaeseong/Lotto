package com.daeseong.lottoplayer.Util

import android.os.AsyncTask
import android.os.SystemClock
import android.webkit.WebView

class LoadUrlTask(private val webView: WebView) : AsyncTask<String?, Void?, String>() {

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: String?): String? {
        SystemClock.sleep(1000)
        return params.getOrNull(0)
    }

    override fun onPostExecute(result: String?) {
        result?.let {
            webView.loadUrl(it)
        }
    }
}
