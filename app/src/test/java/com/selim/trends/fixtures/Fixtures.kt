package com.selim.trends.fixtures

import com.selim.trends.data.genres.Genre
import com.selim.trends.data.genres.GenresResponse
import com.selim.trends.data.movies.Actor
import com.selim.trends.data.movies.Movie
import com.selim.trends.data.movies.MoviesResponse

object Fixtures {
    fun movie(id: Int) = Movie(
        id,
        title = "Spider-Man",
        genre_ids = intArrayOf(),
        genres = arrayOf(),
        vote_average = 0f,
        overview = "",
        release_date = "",
        poster_path = "",
        backdrop_path = "",
        runtime = 90
    )

    fun movieResponse() = MoviesResponse(
        arrayListOf(
            movie(1),
            movie(2)
        )
    )

    fun genresResponse() = GenresResponse(
        arrayListOf(
            Genre(1, TEST_GENRE_1),
            Genre(2, TEST_GENRE_2),
            Genre(3, TEST_GENRE_3),
        )
    )

    const val TEST_GENRE_1 = "Action"
    const val TEST_GENRE_2 = "Thriller"
    const val TEST_GENRE_3 = "Comedy"
}
