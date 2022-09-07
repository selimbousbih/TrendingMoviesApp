package com.selim.trends.features.home

import com.selim.trends.TestCoroutineRule
import com.selim.trends.data.genres.GenresRepository
import com.selim.trends.data.genres.GenresResolver
import com.selim.trends.data.movies.MoviesRepository
import com.selim.trends.fixtures.Fixtures
import com.selim.trends.fixtures.Fixtures.genresResponse
import com.selim.trends.fixtures.Fixtures.movieResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.thinkit.whatshot.functional.right
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    private val moviesRepository: MoviesRepository = mockk()
    private val genresRepository: GenresRepository = mockk()
    private val genreResolver: GenresResolver = GenresResolver()

    lateinit var viewModel: HomeViewModel

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setup() {
        coEvery { genresRepository.getGenres() } returns right(genresResponse())
        coEvery { moviesRepository.getDiscovery(any()) } returns right(movieResponse())

        viewModel = HomeViewModel(moviesRepository, genresRepository, genreResolver)

        coEvery { moviesRepository.getMovies() } returns right(movieResponse())
    }

    @Test
    fun `trending should return expected result`() {
        runBlocking {
            viewModel.trending().collect {
                assert(it == movieResponse().results)
            }
        }
    }

    @Test
    fun `should make discovery call for every genre`() {
        val numberOfGenres = 3
        runBlocking {
            viewModel.discovery(numberOfGenres).collect { }
        }
        coVerify(exactly = numberOfGenres) { moviesRepository.getDiscovery(any()) }
    }

    @Test
    fun `fills genres resolver correctly`() {
        val expected = mapOf(
            1 to Fixtures.TEST_GENRE_1,
            2 to Fixtures.TEST_GENRE_2,
            3 to Fixtures.TEST_GENRE_3,
        )

        assert(genreResolver.genres == expected)
    }
}