package com.selim.trends.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Launches an Activity
 * @param extras a bundle initializer
 */
inline fun <reified T : Activity> Context.launchActivity(extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, T::class.java).apply {
        putExtras(Bundle().apply(extras))
    }
    startActivity(intent)
}
