package com.selim.trends.features.details

import androidx.lifecycle.ViewModel
import com.selim.trends.data.movies.Actor
import com.selim.trends.data.movies.Movie
import com.selim.trends.data.movies.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.thinkit.whatshot.functional.coFold
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.selim.trends.utils.handleError

@HiltViewModel
class DetailsViewModel
@Inject
constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    fun movie(id: Int): Flow<Movie> = flow {
        moviesRepository.getMovieDetails(id).coFold(::handleError) { emit(it) }
    }

    fun cast(id: Int): Flow<List<Actor>> = flow {
        moviesRepository.getCredits(id).coFold(::handleError) { emit(it.cast) }
    }
}
