package com.selim.trends.features.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.selim.components.movie.MovieInfoComponent
import com.selim.trends.R
import com.selim.trends.data.movies.Movie
import com.selim.trends.networking.ApiHelper.getImagePath
import com.selim.trends.utils.bufferingMutableFlow
import com.selim.trends.utils.inflateUnattached
import kotlin.properties.Delegates


class DiscoveryAdapter : RecyclerView.Adapter<DiscoveryAdapter.MoviesViewHolder>() {
    var onMovieClicked = bufferingMutableFlow<Movie>()

    var items: List<Movie> by Delegates.observable(emptyList()) { _, old, new ->
        notifyChanges(old, new)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesViewHolder(parent.inflateUnattached(R.layout.component_movie_info))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = items[position]
        val movieComponent = holder.component as MovieInfoComponent

        movieComponent.render(movie.toInfoViewState())

        holder.itemView.setOnClickListener {
            onMovieClicked.tryEmit(movie)
        }
    }

    private fun notifyChanges(old: List<Movie>, new: List<Movie>) {
        DiffUtil.calculateDiff(Movie.DiffUtilCallback(old, new)).apply {
            dispatchUpdatesTo(this@DiscoveryAdapter)
        }
    }

    inner class MoviesViewHolder(val component: View) :
        RecyclerView.ViewHolder(component)
}

fun Movie.toInfoViewState(): MovieInfoComponent.ViewState = MovieInfoComponent.ViewState(
    title = title,
    imageUrl = getImagePath(poster_path),
    year = release_date.split("-").first(),
)
