package com.selim.trends.features.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selim.trends.data.movies.MoviesResponse
import com.selim.trends.data.genres.GenresRepository
import com.selim.trends.data.genres.GenresResolver
import com.selim.trends.data.movies.Movie
import com.selim.trends.data.movies.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.thinkit.whatshot.functional.Failure
import io.thinkit.whatshot.functional.coFold
import com.selim.trends.utils.handleError
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val moviesRepository: MoviesRepository,
    private val genresRepository: GenresRepository,
    private val genresResolver: GenresResolver
) : ViewModel() {

    var homeState = MutableSharedFlow<HomeState>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    init {
        getGenres()
    }

    fun trending(): Flow<List<Movie>> = flow {
        moviesRepository.getMovies().coFold(::handleError) { emit(it.results) }
    }

    fun discovery(numberOfGenres: Int): Flow<Pair<String, MoviesResponse>> = flow {
        homeState.tryEmit(HomeState.LOADING)
        genresResolver.genres.entries
            .shuffled()
            .take(numberOfGenres)
            .forEach { genreMap ->
                val current = moviesRepository.getDiscovery(intArrayOf(genreMap.key))
                current.coFold(::handleError) { movies -> emit(genreMap.value to movies) }
            }
        homeState.tryEmit(HomeState.STOP_LOADING)
    }

    private fun getGenres() {
        viewModelScope.launch {
            val result = genresRepository.getGenres()
            result.coFold(::handleError) { genresResponse ->
                genresResponse.genres.map { genresResolver.genres[it.id] = it.name }
            }
            homeState.tryEmit(HomeState.READY)
        }
    }

    enum class HomeState {
        READY, LOADING, STOP_LOADING
    }
}
