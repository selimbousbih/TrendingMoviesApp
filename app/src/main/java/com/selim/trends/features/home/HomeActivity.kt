package com.selim.trends.features.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.selim.components.sections.SectionCarousel
import com.selim.trends.data.movies.Movie
import com.selim.trends.databinding.ActivityHomeBinding
import com.selim.trends.features.details.DetailsActivity
import com.selim.trends.features.details.DetailsActivity.Companion.MOVIE_ID_KEY
import com.selim.trends.utils.launchActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        observeState()
    }

    private fun observeState() {
        viewModel.homeState.onEach {
            when (it) {
                HomeViewModel.HomeState.LOADING -> {
                    isLoading(true)
                }
                HomeViewModel.HomeState.STOP_LOADING -> {
                    isLoading(false)
                }
                HomeViewModel.HomeState.READY -> {
                    getTrending()
                    getDiscovery()
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun setupViews() = with(binding) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = MoviesAdapter().apply {
                onMovieClicked
                    .onEach { openMovieDetails(it.id) }
                    .launchIn(lifecycleScope)
            }

            set3DItem(true)
            setIntervalRatio(.65f)
            setAlpha(true)
            setInfinite(true)
        }

        root.setOnRefreshListener {
            getDiscovery()
            binding.root.isRefreshing = false
        }
    }

    private fun getTrending() = with(binding) {
        val adapter = recyclerView.adapter as MoviesAdapter
        viewModel.trending()
            .onEach { adapter.items = it }
            .launchIn(lifecycleScope)
    }

    private fun getDiscovery() = with(binding) {
        homeSectionsAppend.removeAllViews()
        viewModel.discovery(numberOfGenres = DISCOVERY_GENRES_COUNT)
            .onEach { (genre, movies) -> setupDiscoverySections(genre, movies.results) }
            .launchIn(lifecycleScope)
    }

    private fun isLoading(isLoading: Boolean) {
        binding.shimmerLayout.apply {
            if (isLoading) startShimmer() else stopShimmer()
            isVisible = isLoading
        }
    }

    private fun setupDiscoverySections(genre: String, movies: List<Movie>) {
        binding.homeSectionsAppend.addView(
            SectionCarousel<DiscoveryAdapter.MoviesViewHolder>(this).apply {
                render(SectionCarousel.ViewState(title = genre))
                setupAdapter(DiscoveryAdapter().apply {
                    items = movies
                    onMovieClicked.onEach { openMovieDetails(it.id) }.launchIn(lifecycleScope)
                })
            }
        )
    }

    private fun openMovieDetails(id: Int) {
        launchActivity<DetailsActivity> { putInt(MOVIE_ID_KEY, id) }
    }

    companion object {
        const val DISCOVERY_GENRES_COUNT = 4
    }
}
