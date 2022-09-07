package com.selim.trends.features.details

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.selim.trends.databinding.ActivityDetailsBinding
import com.selim.trends.networking.ApiHelper
import com.selim.trends.networking.ApiHelper.ImageSize.ORIGINAL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var renderer: DetailsRenderer

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFullScreen()

        renderer = DetailsRenderer(binding)
        val movieId = intent.getIntExtra(MOVIE_ID_KEY, 0)

        setupViews()
        getMovie(movieId)
    }


    private fun setupViews() = with(binding) {
        imageBack.setOnClickListener { finish() }
        castRecyclerView.layoutManager =
            LinearLayoutManager(this@DetailsActivity, LinearLayoutManager.HORIZONTAL, false)
        castRecyclerView.adapter = CastAdapter()
    }

    private fun getMovie(id: Int) {
        viewModel.movie(id)
            .onEach { movie ->
                renderer.render(
                    DetailsRenderer.ViewState(
                        title = movie.title,
                        overview = movie.overview,
                        genre = "Action",
                        genres = movie.genres,
                        rating = movie.vote_average,
                        backdrop_url = ApiHelper.getImagePath(movie.backdrop_path, ORIGINAL),
                        runtime = movie.runtime,
                        releaseDate = movie.release_date
                    )
                )
            }
            .launchIn(lifecycleScope)

        viewModel.cast(id)
            .onEach { actors ->
                (binding.castRecyclerView.adapter as CastAdapter).apply {
                    items = actors
                }
            }
            .launchIn(lifecycleScope)
    }

    fun setupFullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    companion object {
        const val MOVIE_ID_KEY = "MOVIE_ID"
    }
}

