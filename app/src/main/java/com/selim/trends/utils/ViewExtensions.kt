package com.selim.trends.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Rect
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.TouchDelegate
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.annotation.Px
import androidx.core.view.children
import androidx.core.view.isVisible

fun ViewGroup.inflateUnattached(layoutId: Int): View =
    LayoutInflater.from(context).inflate(layoutId, this, false)

fun View.show(duration: Long = 200, onShow: () -> Unit = {}) {
    animate().alpha(1.0f).setDuration(duration).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator) {
            super.onAnimationStart(animation)
            isVisible = true
        }

        override fun onAnimationEnd(animation: Animator?) {
            super.onAnimationEnd(animation)
            onShow()
        }
    })
}

fun View.hide(duration: Long = 200, onHide: () -> Unit = {}) {
    animate().alpha(0.0f).setDuration(duration)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                isVisible = false
                onHide()
            }
        })
}
