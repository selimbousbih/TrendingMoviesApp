package com.selim.components.sections

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.selim.components.databinding.LayoutSectionCarouselBinding

class SectionCarousel<VH : RecyclerView.ViewHolder>
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutSectionCarouselBinding =
        LayoutSectionCarouselBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.sectionRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun render(state: ViewState) = with(binding) {
        viewState = state
    }

    fun setupAdapter(adapter: RecyclerView.Adapter<VH>) = with(binding) {
        sectionRecyclerView.adapter = adapter
    }

    data class ViewState(
        val title: String,
    )
}
