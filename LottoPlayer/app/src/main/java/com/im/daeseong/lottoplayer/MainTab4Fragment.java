package com.im.daeseong.lottoplayer;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;//import android.support.annotation.Nullable;
import androidx.fragment.app.Fragment;//import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.im.daeseong.lottoplayer.Util.loadUrlTask;

public class MainTab4Fragment extends Fragment {

    private static final String TAG = MainTab4Fragment.class.getSimpleName();

    private Context mContext;

    private WebView wvWebView;
    private ProgressBar pProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = container.getContext();
        return inflater.inflate(R.layout.fragment_main_tab4, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        wvWebView = (WebView)view.findViewById(R.id.webview);
        pProgressBar = (ProgressBar)view.findViewById(R.id.progressbar);

        try {
            if (LottoApplication.getInstance().isNetworkAvailable(mContext)) {
                initWebview("https://m.dhlottery.co.kr/common.do?method=main");
            } else {
                initWebview("about:blank");
            }
        }catch (Exception e){
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try {
            clearWebViewResource();
            System.gc();
        }catch (Exception e){
        }
    }

    public void clearWebViewResource() {
        try {
            if (wvWebView != null) {
                wvWebView.removeAllViews();
                ((ViewGroup) wvWebView.getParent()).removeView(wvWebView);
                wvWebView.setTag(null);
                wvWebView.clearHistory();
                wvWebView.destroy();
                wvWebView = null;
            }
        }catch (Exception e){
        }
    }

    private void initWebview(String url) {

        // 웹뷰 세팅
        WebSettings webSettings = wvWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);// 자바 스크립트 사용

        webSettings.setSupportZoom(false);//확대 축소 기능
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//캐시모드를 사용하지 않고 네트워크를 통해서만 호출
        //webSettings.setAppCacheEnabled(false);//앱 내부 캐시 사용 여부 설정

        webSettings.setUseWideViewPort(true);//웹뷰에 맞게 출력하기
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(false); // 안드로이드 내장 줌 컨트롤 사용 X

        //WebViewClient
        wvWebView.setWebViewClient(new WebViewClient() {

            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }

            //timeout error check
            private Runnable timeoutRunnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        wvWebView.stopLoading();
                        pProgressBar.setVisibility(View.GONE);
                    }catch (Exception e){
                    }
                }
            };

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                String message = "SSL Certificate error.";
                switch (error.getPrimaryError()) {
                    case SslError.SSL_UNTRUSTED:
                        message = "The certificate authority is not trusted.";
                        break;
                    case SslError.SSL_EXPIRED:
                        message = "The certificate has expired.";
                        break;
                    case SslError.SSL_IDMISMATCH:
                        message = "The certificate Hostname mismatch.";
                        break;
                    case SslError.SSL_NOTYETVALID:
                        message = "The certificate is not yet valid.";
                        break;
                }
                message += "\"SSL Certificate Error\" Do you want to continue anyway?.. YES";

                handler.proceed();//SSL 에러가 발생해도 계속 진행
            }
        });

        wvWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                return true;
            };

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                return true;
            };

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            };

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    pProgressBar.setVisibility(View.GONE);
                } else {
                    if (pProgressBar.getVisibility() == View.GONE){
                        pProgressBar.setVisibility(View.VISIBLE);
                    }
                    pProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });

        wvWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && wvWebView.canGoBack()) {
                        wvWebView.goBack();
                        return true;
                    }
                }
                return false;
            }
        });

        new loadUrlTask(wvWebView).execute(url);
    }
}
