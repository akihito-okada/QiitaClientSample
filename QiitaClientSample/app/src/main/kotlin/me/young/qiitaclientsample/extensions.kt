package me.young.qiitaclientsample

import android.app.Activity
import android.support.annotation.IdRes
import android.view.View

/**
 * Created by Akihito on 2017/07/30.
 */

fun <T : View> View.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}

fun <T : View> Activity.bindView(@IdRes id: Int): Lazy<T> = lazy{
    findViewById(id) as T
}