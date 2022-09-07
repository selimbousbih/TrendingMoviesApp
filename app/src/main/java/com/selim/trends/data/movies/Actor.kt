package com.selim.trends.data.movies

import androidx.recyclerview.widget.DiffUtil

data class Actor(
    val name: String,
    val character: String,
    val profile_path: String,
) {
    class DiffUtilCallback(private val oldItems: List<Actor>, private val newItems: List<Actor>) :
        DiffUtil.Callback() {
        override fun getOldListSize() = oldItems.size

        override fun getNewListSize() = newItems.size

        override fun areItemsTheSame(oldItemIndex: Int, newItemIndex: Int): Boolean {
            return oldItems[oldItemIndex].name == newItems[newItemIndex].name
        }

        override fun areContentsTheSame(oldItemIndex: Int, newItemIndex: Int): Boolean {
            return oldItems[oldItemIndex].name == newItems[newItemIndex].name &&
                    oldItems[oldItemIndex].profile_path == newItems[newItemIndex].profile_path
        }
    }
}