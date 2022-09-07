package com.selim.trends.utils

import android.util.Log
import androidx.lifecycle.ViewModel
import io.thinkit.whatshot.functional.Failure


fun ViewModel.handleError(error: Failure) {
    if (error is Failure.NetworkError) {
        Log.d("Failure", "Network Error")
    }
}