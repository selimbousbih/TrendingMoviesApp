package com.selim.trends.networking

import com.selim.trends.data.movies.MoviesResponse
import com.selim.trends.data.genres.GenresResponse
import com.selim.trends.data.movies.CreditsResponse
import com.selim.trends.data.movies.Movie
import com.selim.trends.networking.Endpoints.CREDITS
import com.selim.trends.networking.Endpoints.DISCOVER
import com.selim.trends.networking.Endpoints.GENRES
import com.selim.trends.networking.Endpoints.MOVIE
import com.selim.trends.networking.Endpoints.TRENDING
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Api {
    @GET(DISCOVER)
    suspend fun getDiscovery(@Query("with_genres") genreIds: IntArray): MoviesResponse

    @GET(TRENDING)
    suspend fun getMovies(): MoviesResponse

    @GET(GENRES)
    suspend fun getGenres(): GenresResponse

    @GET(MOVIE)
    suspend fun getMovieDetails(@Path("id") id: Int): Movie

    @GET(CREDITS)
    suspend fun getMovieCredits(@Path("id") id: Int): CreditsResponse
}
