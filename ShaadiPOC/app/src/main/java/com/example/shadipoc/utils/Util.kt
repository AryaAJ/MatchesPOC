package com.example.shadipoc.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import java.text.SimpleDateFormat
import java.util.*

fun isNetworkAvailable(mContext: Context?): Boolean {
    if (mContext != null) {
        val connManager: ConnectivityManager = mContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val info: NetworkInfo? = connManager.activeNetworkInfo
        return info != null && info.isConnected

    }
    return false
}

fun <T : ViewModel> Fragment.getViewModel(
    modelClass: Class<T>,
    viewModelFactory: ViewModelProvider.Factory? = null
): T {
    return viewModelFactory?.let { ViewModelProviders.of(this, it).get(modelClass) }
        ?: ViewModelProviders.of(this).get(modelClass)
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}


inline fun androidx.fragment.app.FragmentManager.inTransaction(
    fragmentName: String,
    addToBackStack: Boolean,
    func: androidx.fragment.app.FragmentTransaction.() -> androidx.fragment.app.FragmentTransaction
) {
    if (addToBackStack) {
        beginTransaction().func().addToBackStack(fragmentName).commit()
    } else {
        beginTransaction().func().commit()
    }
}

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    addToBackStack: Boolean,
    fragmentName: String,
    containerId: Int,
    data: Bundle?
) {
    if (data != null) {
        fragment.arguments = data
    } else
        fragment.arguments = null


    supportFragmentManager.inTransaction(fragmentName, addToBackStack) {
        replace(
            containerId,
            fragment,
            fragmentName
        )
    }
}

@SuppressLint("SimpleDateFormat")
fun getCurrentTimeStamp(milli: String): String {
    return SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(Date(milli.toLong())).toString()
}