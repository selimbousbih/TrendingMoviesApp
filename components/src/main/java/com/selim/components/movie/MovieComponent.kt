package com.selim.components.movie

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.selim.components.databinding.LayoutMovieBinding

class MovieComponent
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutMovieBinding =
        LayoutMovieBinding.inflate(LayoutInflater.from(context), this, true)

    fun render(state: ViewState) = with(binding) {
        viewState = state
    }

    data class ViewState(
        val title: String,
        val imageUrl: String?,
    )
}
