package com.daeseong.lottoplayer


import android.annotation.TargetApi
import android.content.Context
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.daeseong.lottoplayer.Util.loadUrlTask


class MainTab4Fragment : Fragment() {

    companion object {
        private val tag = MainTab4Fragment::class.java.simpleName
    }

    private var mContext: Context? = null
    private var wvWebView: WebView? = null
    private var pProgressBar: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mContext = container!!.context
        return inflater.inflate(R.layout.fragment_main_tab4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wvWebView = view.findViewById<View>(R.id.webview) as WebView
        pProgressBar = view.findViewById<View>(R.id.progressbar) as ProgressBar

        try {
            if (LottoApplication.getInstance().isNetworkAvailable(mContext!!)) {
                initWebview("https://m.dhlottery.co.kr/common.do?method=main")
            } else {
                initWebview("about:blank")
            }
        } catch (e: Exception) {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        try {
            clearWebViewResource()
            System.gc()
        } catch (e: Exception) {
        }
    }

    fun clearWebViewResource() {
        try {
            if (wvWebView != null) {
                wvWebView!!.removeAllViews()
                (wvWebView!!.parent as ViewGroup).removeView(wvWebView)
                wvWebView!!.tag = null
                wvWebView!!.clearHistory()
                wvWebView!!.destroy()
                wvWebView = null
            }
        } catch (e: Exception) {
        }
    }

    private fun initWebview(url: String) {

        // 웹뷰 세팅
        val webSettings = wvWebView!!.settings
        webSettings.javaScriptEnabled = true // 자바 스크립트 사용
        webSettings.setSupportZoom(false) //확대 축소 기능
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE //캐시모드를 사용하지 않고 네트워크를 통해서만 호출
        webSettings.setAppCacheEnabled(false) //앱 내부 캐시 사용 여부 설정
        webSettings.useWideViewPort = true //웹뷰에 맞게 출력하기
        webSettings.loadWithOverviewMode = true
        webSettings.builtInZoomControls = false // 안드로이드 내장 줌 컨트롤 사용 X

        //WebViewClient
        wvWebView!!.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return true
            }

            //timeout error check
            private val timeoutRunnable = Runnable {
                try {
                    wvWebView!!.stopLoading()
                    pProgressBar!!.visibility = View.GONE
                } catch (e: Exception) {
                }
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
                super.onReceivedError(view, request, error)
            }

            @TargetApi(Build.VERSION_CODES.M)
            override fun onReceivedHttpError(view: WebView, request: WebResourceRequest, errorResponse: WebResourceResponse) {
                super.onReceivedHttpError(view, request, errorResponse)
            }

            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {

                var message = "SSL Certificate error."
                when (error.primaryError) {
                    SslError.SSL_UNTRUSTED -> message = "The certificate authority is not trusted."
                    SslError.SSL_EXPIRED -> message = "The certificate has expired."
                    SslError.SSL_IDMISMATCH -> message = "The certificate Hostname mismatch."
                    SslError.SSL_NOTYETVALID -> message = "The certificate is not yet valid."
                }
                message += "\"SSL Certificate Error\" Do you want to continue anyway?.. YES"
                handler.proceed() //SSL 에러가 발생해도 계속 진행
            }
        }

        wvWebView!!.webChromeClient = object : WebChromeClient() {

            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                return true
            }

            override fun onJsConfirm(view: WebView,url: String, message: String, result: JsResult): Boolean {
                return true
            }

            override fun onJsPrompt(view: WebView,url: String, message: String, defaultValue: String, result: JsPromptResult): Boolean {
                return super.onJsPrompt(view, url, message, defaultValue, result)
            }

            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (newProgress == 100) {
                    pProgressBar!!.visibility = View.GONE
                } else {
                    if (pProgressBar!!.visibility == View.GONE) {
                        pProgressBar!!.visibility = View.VISIBLE
                    }
                    pProgressBar!!.progress = newProgress
                }
                super.onProgressChanged(view, newProgress)
            }

            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)
            }
        }

        wvWebView!!.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_BACK && wvWebView!!.canGoBack()) {
                    wvWebView!!.goBack()
                    return@OnKeyListener true
                }
            }
            false
        })

        loadUrlTask(wvWebView!!).execute(url)
    }
}
