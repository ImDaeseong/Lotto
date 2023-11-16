package com.daeseong.lottoplayer.Util

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WebViewViewModel : ViewModel() {

    private val _urlToLoad = MutableLiveData<String?>()
    val urlToLoad: LiveData<String?> get() = _urlToLoad

    fun loadUrl(url: String) {
        GlobalScope.launch(Dispatchers.IO) {
            SystemClock.sleep(1000)
            _urlToLoad.postValue(url)
        }
    }
}