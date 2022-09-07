package com.selim.trends.data.genres

import com.selim.trends.networking.Api
import io.thinkit.whatshot.functional.Either
import io.thinkit.whatshot.functional.Failure
import io.thinkit.whatshot.functional.left
import io.thinkit.whatshot.functional.right
import java.lang.Exception
import javax.inject.Inject

class GenresRepository
@Inject
constructor(
    private var api: Api
){
    suspend fun getGenres(): Either<Failure, GenresResponse> {
        return try {
            right(api.getGenres())
        } catch (e: Exception) {
            left(Failure.NetworkError())
        }
    }
}