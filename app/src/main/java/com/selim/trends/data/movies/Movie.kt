package com.selim.trends.data.movies

import androidx.recyclerview.widget.DiffUtil
import com.selim.trends.data.genres.Genre

data class Movie (
    val id: Int,
    val title: String,
    val genre_ids: IntArray,
    val genres: Array<Genre>,
    val vote_average: Float,
    val overview: String,
    val release_date: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val runtime: Int,
) {
    class DiffUtilCallback(private val oldItems: List<Movie>, private val newItems: List<Movie>) :
        DiffUtil.Callback() {
        override fun getOldListSize() = oldItems.size

        override fun getNewListSize() = newItems.size

        override fun areItemsTheSame(oldItemIndex: Int, newItemIndex: Int): Boolean {
            return oldItems[oldItemIndex].id == newItems[newItemIndex].id
        }

        override fun areContentsTheSame(oldItemIndex: Int, newItemIndex: Int): Boolean {
            return oldItems[oldItemIndex].id == newItems[newItemIndex].id
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}