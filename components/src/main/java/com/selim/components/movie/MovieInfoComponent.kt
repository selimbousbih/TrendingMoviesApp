package com.selim.components.movie

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.selim.components.databinding.LayoutMovieInfoBinding

class MovieInfoComponent
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutMovieInfoBinding =
        LayoutMovieInfoBinding.inflate(LayoutInflater.from(context), this, true)

    fun render(state: ViewState) = with(binding) {
        viewState = state
    }

    data class ViewState(
        val title: String,
        val year: String,
        val imageUrl: String?,
    )
}
