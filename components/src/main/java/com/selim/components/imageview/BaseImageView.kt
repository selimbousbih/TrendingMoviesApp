package com.selim.components.imageview

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.doOnPreDraw
import com.google.android.material.imageview.ShapeableImageView
import com.selim.components.R
import com.selim.components.extendTouchArea


open class BaseImageView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ShapeableImageView(context, attrs) {

    init {
        doOnPreDraw {
            extendTouchArea(R.dimen.spacing_s)
        }
    }
}
