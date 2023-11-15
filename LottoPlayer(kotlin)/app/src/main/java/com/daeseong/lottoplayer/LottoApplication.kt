package com.daeseong.lottoplayer

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.net.ConnectivityManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast

class LottoApplication : Application() {

    private val tag = LottoApplication::class.java.simpleName

    companion object {

        private lateinit var mContext: Context
        private lateinit var mInstance: LottoApplication

        fun getInstance(): LottoApplication {
            return mInstance
        }

        private var toast: Toast? = null


    }

    override fun onCreate() {
        super.onCreate()

        mContext = this
        mInstance = this
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    fun Toast(context: Context, sMsg: String?, bLengthLong: Boolean) {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.toast_layout, null)
        val tvtoast: TextView = view.findViewById(R.id.tvtoast)

        //최대 1줄까지
        tvtoast.maxLines = 1
        tvtoast.setTextColor(Color.parseColor("#000000"))
        tvtoast.text = sMsg
        toast = Toast(context)
        toast!!.setGravity(Gravity.BOTTOM, 0, 0)//toast!!.setGravity(Gravity.CENTER, 0, 0)
        if (bLengthLong) {
            toast!!.duration = Toast.LENGTH_LONG
        } else {
            toast!!.duration = Toast.LENGTH_SHORT
        }
        toast!!.view = view
        toast!!.show()
    }

    fun Toastcancel() {
        try {
            toast!!.cancel()
        } catch (e: Exception) {
        }
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}