package com.selim.trends.data.movies

import com.selim.trends.networking.Api
import io.thinkit.whatshot.functional.Either
import io.thinkit.whatshot.functional.Failure
import io.thinkit.whatshot.functional.left
import io.thinkit.whatshot.functional.right
import java.lang.Exception
import javax.inject.Inject

class MoviesRepository
@Inject
constructor(
    private var api: Api
) {
    suspend fun getDiscovery(genreIds: IntArray): Either<Failure, MoviesResponse> {
        return try {
            right(api.getDiscovery(genreIds))
        } catch (e: Exception) {
            left(Failure.NetworkError())
        }
    }

    suspend fun getMovies(): Either<Failure, MoviesResponse> {
        return try {
            right(api.getMovies())
        } catch (e: Exception) {
            left(Failure.NetworkError())
        }
    }

    suspend fun getMovieDetails(id: Int): Either<Failure, Movie> {
        return try {
            right(api.getMovieDetails(id))
        } catch (e: Exception) {
            left(Failure.NetworkError())
        }
    }

    suspend fun getCredits(id: Int): Either<Failure, CreditsResponse> {
        return try {
            right(api.getMovieCredits(id))
        } catch (e: Exception) {
            left(Failure.NetworkError())
        }
    }
}