package com.selim.trends.features.details

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.selim.components.credits.ActorComponent
import com.selim.trends.R
import com.selim.trends.data.movies.Actor
import com.selim.trends.networking.ApiHelper.getImagePath
import com.selim.trends.utils.inflateUnattached
import kotlin.properties.Delegates


class CastAdapter : RecyclerView.Adapter<CastAdapter.TracksViewHolder>() {
    var items: List<Actor> by Delegates.observable(emptyList()) { _, old, new ->
        notifyChanges(old, new)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TracksViewHolder(parent.inflateUnattached(R.layout.component_actor))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: TracksViewHolder, position: Int) {
        val movie = items[position]
        val actorComponent = holder.component as ActorComponent

        actorComponent.render(movie.toViewState())
    }

    private fun notifyChanges(old: List<Actor>, new: List<Actor>) {
        DiffUtil.calculateDiff(Actor.DiffUtilCallback(old, new)).apply {
            dispatchUpdatesTo(this@CastAdapter)
        }
    }

    inner class TracksViewHolder(val component: View) :
        RecyclerView.ViewHolder(component)
}

fun Actor.toViewState(): ActorComponent.ViewState = ActorComponent.ViewState(
    name = name,
    character = character,
    imageUrl = getImagePath(profile_path),
)
