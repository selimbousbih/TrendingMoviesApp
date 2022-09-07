package com.selim.components

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.TouchDelegate
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.annotation.Px


private fun View.extendTouchAreaBy(@Px extendPx: Int) {
    val parent = (parent ?: return) as View
    parent.post {
        val bounds = Rect()
        getHitRect(bounds)
        bounds.top -= extendPx
        bounds.left -= extendPx
        bounds.right += extendPx
        bounds.bottom += extendPx
        parent.touchDelegate = TouchDelegate(bounds, this)
    }
}

fun View.extendTouchArea(@DimenRes dimen: Int) {
    val extendPx = resources.getDimensionPixelSize(dimen)
    extendTouchAreaBy(extendPx)
}

fun ViewGroup.inflateUnattached(layoutId: Int): View =
    LayoutInflater.from(context).inflate(layoutId, this, false)
