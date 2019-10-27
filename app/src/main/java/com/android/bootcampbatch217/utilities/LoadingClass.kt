package com.android.bootcampbatch217.utilities

import android.app.ProgressDialog
import android.content.Context
import android.widget.ProgressBar
import com.android.bootcampbatch217.R

fun Context.loadingAnimationAndText(text:String):ProgressDialog{
    val loading = ProgressDialog(this, R.style.CustomLoadingStyle)
    loading.setProgressStyle(android.R.style.Widget_ProgressBar_Small)
    loading.setCancelable(false)
    loading.setCancelable(false)
    loading.setCanceledOnTouchOutside(false)
    loading.setMessage(text)

    return loading
}

fun Context.loadingAnimation():ProgressDialog{
    val loading = ProgressDialog(this, R.style.CustomLoadingStyle)
    loading.setProgressStyle(android.R.style.Widget_ProgressBar_Small)
    loading.setCancelable(false)
    loading.setCancelable(false)
    loading.setCanceledOnTouchOutside(false)

    return loading
}