package com.daeseong.lottoplayer

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.lottoplayer.Database.CopyDBfile
import com.daeseong.lottoplayer.Util.RecycleUtil.recursiveRecycle


class SplashActivity : AppCompatActivity() {

    private val TAG = SplashActivity::class.java.simpleName

    private var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        InitTitleBar()

        setContentView(R.layout.activity_splash)

        init()
        InitData()
    }

    private fun init() {
        handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    0 -> {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }

    private fun InitData() {

        try {
            val bCopy = CopyDBTask().execute(this).get()
        } catch (e: java.lang.Exception) {
        }

        handler!!.postDelayed({
            try {
                val msg = handler!!.obtainMessage()
                msg.what = 0
                handler!!.sendMessage(msg)
            } catch (e: java.lang.Exception) {
            }
        }, 1000)
    }

    //타이틀바 숨기기/가로보기 고정/풀스크린
    private fun InitTitleBar() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
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

        try {
            if (handler != null) {
                handler!!.removeCallbacksAndMessages(null)
                handler = null
            }
            recursiveRecycle(window.decorView)
            System.gc()
        } catch (e: Exception) {
        }
    }

    inner class CopyDBTask : AsyncTask<Context?, Void?, Boolean>() {

        override fun doInBackground(vararg params: Context?): Boolean? {

            try {

                CopyDBfile(params[0]!!)
                return true

            } catch (e: java.lang.Exception) {
            }

            return false
        }
    }

}
