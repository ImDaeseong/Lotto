package com.daeseong.lottoplayer.Util

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import androidx.core.view.children
import java.lang.ref.WeakReference

object RecycleUtil {

    fun recursiveRecycle(root: View?) {
        root?.apply {
            background = null

            if (this is ViewGroup) {
                val group = this
                val count = group.childCount
                for (i in 0 until count) {
                    recursiveRecycle(group.getChildAt(i))
                }

                if (this !is AdapterView<*>) {
                    group.removeAllViews()
                }
            }

            if (this is ImageView) {
                setImageDrawable(null)
            }
        }
    }

    fun recursiveRecycle(recycleList: List<WeakReference<View?>>) {
        recycleList.forEach { ref -> recursiveRecycle(ref.get()) }
    }

    fun unBindDrawables(view: View) {
        view.background?.callback = null
        if (view is ViewGroup && view !is AdapterView<*>) {
            view.children.forEach { child -> unBindDrawables(child) }
            view.removeAllViews()
        }
    }
}
