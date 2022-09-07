package com.selim.trends.features.details

import android.graphics.text.LineBreaker
import android.os.Build
import com.selim.components.imageUrl
import com.selim.trends.R
import com.selim.trends.data.genres.Genre
import com.selim.trends.databinding.ActivityDetailsBinding

class DetailsRenderer(private val binding: ActivityDetailsBinding) {

    fun render(state: ViewState) = with(binding) {
        movieTitle.text = state.title
        movieOverview.apply {
            text = state.overview
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
            }
        }
        movieInfo.text = root.resources.getString(
            R.string.movie_info,
            releaseYear(state.releaseDate),
            genres(state.genres),
            durationString(state.runtime)
        )
        backdrop.imageUrl(state.backdrop_url)
        ratingBar.rating = state.rating * 0.5f
    }

    private fun releaseYear(date: String): String {
        return date.split("-").first()
    }

    private fun genres(genres: Array<Genre>): String {
        return genres.joinToString(", ") { it.name }
    }

    private fun durationString(durationInMinutes: Int): String {
        val m: Int = durationInMinutes % 60
        val h: Int = durationInMinutes / 60
        return if (h > 0) "%dh %02dm".format(h, m) else "%02dm".format(m)
    }

    data class ViewState(
        val title: String,
        val overview: String,
        val genre: String?,
        val genres: Array<Genre>,
        val rating: Float,
        val backdrop_url: String?,
        val runtime: Int,
        val releaseDate: String,
    )
}

