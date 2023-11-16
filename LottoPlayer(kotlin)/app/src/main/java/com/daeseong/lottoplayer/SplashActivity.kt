package com.daeseong.lottoplayer

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.*
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.lottoplayer.Database.CopyDBfile
import com.daeseong.lottoplayer.Util.RecycleUtil.recursiveRecycle
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    private val tag = SplashActivity::class.java.simpleName

    private var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        InitTitleBar()

        setContentView(R.layout.activity_splash)

        init()
        InitData()
    }

    private fun init() {
        handler = Handler(Looper.getMainLooper()) {
            when (it.what) {
                0 -> {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    private fun InitData() {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                CopyDBfile(this@SplashActivity)
            } catch (e: Exception) {
            }

            launch(Dispatchers.Main) {
                delay(1000)
                handler?.sendEmptyMessage(0)
            }
        }
    }

    //타이틀바 숨기기/가로보기 고정/풀스크린
    private fun InitTitleBar() {

        try {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        } catch (ex: Exception) {
        }

        try {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } catch (ex: Exception) {
        }
    }

    override fun onBackPressed() {
        //super.onBackPressed();
        //백버튼 기능 막음
        return
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()

        handler?.removeCallbacksAndMessages(null)
        recursiveRecycle(window.decorView)
        System.gc()
    }
}
