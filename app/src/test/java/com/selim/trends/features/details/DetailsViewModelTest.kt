package com.selim.trends.features.details

import com.selim.trends.TestCoroutineRule
import com.selim.trends.data.genres.GenresRepository
import com.selim.trends.data.genres.GenresResolver
import com.selim.trends.data.movies.MoviesRepository
import com.selim.trends.fixtures.Fixtures
import com.selim.trends.fixtures.Fixtures.genresResponse
import com.selim.trends.fixtures.Fixtures.movie
import com.selim.trends.fixtures.Fixtures.movieResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.thinkit.whatshot.functional.right
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailsViewModelTest {
    private val moviesRepository: MoviesRepository = mockk()

    lateinit var viewModel: DetailsViewModel

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setup() {
        coEvery { moviesRepository.getMovieDetails(any()) } returns right(movie(1))
        viewModel = DetailsViewModel(moviesRepository)
    }

    @Test
    fun `should make movie details call`() {
        runBlocking {
            viewModel.movie(1).collect {  }
        }

        coVerify { moviesRepository.getMovieDetails(1) }
    }
}