package com.selim.components.credits

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.selim.components.databinding.LayoutActorBinding

class ActorComponent
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutActorBinding =
        LayoutActorBinding.inflate(LayoutInflater.from(context), this, true)

    fun render(state: ViewState) = with(binding) {
        viewState = state
    }

    data class ViewState(
        val name: String,
        val character: String,
        val imageUrl: String?,
    )
}
