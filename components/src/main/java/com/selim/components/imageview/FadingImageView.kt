package com.selim.components.imageview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.google.android.material.internal.ViewUtils.dpToPx

class FadingImageView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseImageView(context, attrs) {

    private var mFadeSide: FadeSide? = FadeSide.BOTTOM_SIDE

    enum class FadeSide {
        TOP_SIDE, BOTTOM_SIDE
    }

     init {
        isVerticalFadingEdgeEnabled = true
        setEdgeLength(150)
    }

    fun setFadeDirection(side: FadeSide?) {
        mFadeSide = side
    }

    fun setEdgeLength(length: Int) {
        setFadingEdgeLength(dpToPx(context, length).toInt())
    }

    override fun getBottomFadingEdgeStrength(): Float {
        return if (mFadeSide == FadeSide.BOTTOM_SIDE) 1.0f else 0.0f
    }

    override fun getTopFadingEdgeStrength(): Float {
        return if (mFadeSide == FadeSide.TOP_SIDE) 1.0f else 0.0f
    }

    override fun hasOverlappingRendering(): Boolean {
        return true
    }

    public override fun onSetAlpha(alpha: Int): Boolean {
        return false
    }
}